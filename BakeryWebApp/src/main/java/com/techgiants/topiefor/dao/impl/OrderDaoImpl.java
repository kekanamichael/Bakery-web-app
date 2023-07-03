package com.techgiants.topiefor.dao.impl;

import com.techgiants.topiefor.dao.OrderDao;
import com.techgiants.topiefor.db.DBManager;
import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Address;
import com.techgiants.topiefor.model.Delivery;
import com.techgiants.topiefor.model.DeliveryNote;
import com.techgiants.topiefor.model.Invoice;
import com.techgiants.topiefor.model.Order;
import com.techgiants.topiefor.model.OrderItem;
import com.techgiants.topiefor.model.Payment;
import com.techgiants.topiefor.model.Product;
import com.techgiants.topiefor.paymentmethod.PaymentMethod;
import com.techgiants.topiefor.status.Status;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDaoImpl implements OrderDao {

    private static OrderDaoImpl OrderDaoImpl;
    private Connection con = null;
    private PreparedStatement ps;

    private OrderDaoImpl(Connection con) {
        this.con = con;
    }

    public static OrderDaoImpl getInstance(Connection con) {
        if (OrderDaoImpl == null) {
            OrderDaoImpl = new OrderDaoImpl(con);
        }
        return OrderDaoImpl;
    }

    @Override
    public int addOrder(Order order, int id) {
        int retVal = -1;
        if (con == null) {
            return retVal;
        }
        try {
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO `ORDER` (status, orderDate, totalAmount, user_address_id) VALUES(?,?,?,?)");
            ZonedDateTime zonedDateTimeOrder = LocalDateTime.now().atZone(ZoneId.systemDefault());
            Instant insOrd = zonedDateTimeOrder.toInstant();
            java.util.Date orderDate = Date.from(insOrd);

            ps.setString(1, Status.values()[0].name());
            ps.setTimestamp(2, new java.sql.Timestamp(orderDate.getTime()));
            ps.setDouble(3, order.getTotalAmount());
            ps.setInt(4, id);

            if (ps.executeUpdate() > 0) {
                int orderId = getOrderId();

                if (orderId > 0) {
                    retVal = orderId;
                    int cnt = 0;
                    
                    List<OrderItem> items = order.getOrderItems();
                    while (cnt < items.size() && retVal>0) {
                        retVal = addOrderItem(items.get(cnt), orderId)? retVal:-1;
                        cnt++;
                    }
                    if (retVal>0) {
                        addDelivery(order.getDelivery(), orderId);
                        addInvoice(order.getInvoice(), orderId);
                    }
                }

                if (retVal>0) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Err:" + ex.getMessage());
        } finally {
            try {
                if(!con.getAutoCommit()){
                    con.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retVal;
    }

    @Override
    public boolean updateOrderStatus(int orderId, Status status) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }
        try {
            ps = con.prepareStatement("UPDATE `ORDER` SET status=? WHERE orderId=?");
            ps.setString(1, status.name());
            ps.setInt(2, orderId);
            if (ps.executeUpdate() > 0) {
                retVal = true;
                if (status.name().equals(Status.values()[3].name())) {
                    updateDeliveryByOrderId(orderId);
                }
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return retVal;
    }

    @Override
    public boolean addOrderItem(OrderItem item, int orderId) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }

        try {
            ps = con.prepareStatement("INSERT INTO orderItem (product_id, order_id, unitPrice, quantity) VALUES(?,?,?,?)");
            ps.setInt(1, item.getProduct().getProductId());
            ps.setInt(2, orderId);
            ps.setDouble(3, item.getUnitPrice());
            ps.setInt(4, item.getQuantity());
            if (ps.executeUpdate() > 0) {
                retVal = true;
            }
        } catch (SQLException e) {
            System.out.println("Cannot add Order item: " + e.getMessage());
        }

        return retVal;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList();
        if (con == null) {
            return orderList;
        }
        try {
            ps = con.prepareStatement("SELECT us.name, us.email, o.orderId, o.status, o.orderDate, d.deliveryDate, d.delivered, i.isPaid, i.invoiceId, p.paymentId, p.datePaid, p.paymentMethod, p.amount, o.totalAmount, ua.email, ua.addressId, a.addressId "
                    + " FROM user us, `order` o, delivery d, invoice i, user_address ua, address a, payment p "
                    + " WHERE us.email = ua.email "
                    + " AND a.addressId = ua.addressId "
                    + " AND o.orderId = i.orderId "
                    + " AND o.orderId = d.order_id "
                    + " AND o.user_address_Id = ua.id "
                    + " AND i.invoiceId = p.invoiceId ");
            ResultSet rs = ps.executeQuery();
            Order order;
            while (rs.next()) {
                order = new Order(rs.getInt("o.orderId"), rs.getTimestamp("o.orderDate").toLocalDateTime(), Status.valueOf(rs.getString("o.status")), rs.getDouble("o.totalAmount"), new Delivery(rs.getTimestamp("d.deliveryDate") == null ? null : rs.getTimestamp("d.deliveryDate").toLocalDateTime(), rs.getBoolean("d.delivered")), new Invoice(rs.getInt("i.invoiceId"), rs.getBoolean("i.isPaid"), new Payment(rs.getInt("p.paymentId"), PaymentMethod.valueOf(rs.getString("p.paymentMethod")) == null ? null : PaymentMethod.valueOf(rs.getString("p.paymentMethod")), rs.getDouble("p.amount"), rs.getTimestamp("p.datePaid") == null ? null : rs.getTimestamp("p.datePaid").toLocalDateTime())));
                order.setOrderItems(getAllOrderItemsByOrderId(order.getOrderId()));
                order.setUserName(rs.getString("us.name"));
                order.setUserId(rs.getString("us.email"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
        return orderList;
    }

    @Override
    public List<OrderItem> getAllOrderItemsByOrderId(int orderId) {
        List<OrderItem> itemList = new ArrayList();
        if (con == null) {
            return itemList;
        }
        try {

            ps = con.prepareStatement("SELECT oi.orderItemId, oi.product_id, oi.unitPrice, oi.quantity, pro.name FROM orderitem as oi, product as pro WHERE oi.order_id=? AND oi.product_id=pro.productId");
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                itemList.add(new OrderItem(rs.getInt("oi.orderitemId"), rs.getInt("oi.quantity"), rs.getDouble("oi.unitPrice"), new Product(rs.getInt("oi.product_id"), rs.getString("pro.name"))));
            }
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
        return itemList;
    }

    private int getOrderId() throws SQLException {

        ps = con.prepareStatement("SELECT LAST_INSERT_ID()");
        ResultSet rs = ps.executeQuery();
        int lastInsertId = -1;
        if (rs.next()) {
            lastInsertId = rs.getInt(1);
            System.out.println("Last insertVal:" + lastInsertId);
        }
        return lastInsertId;
    }

    private int getInvoiceId() throws SQLException {

        ps = con.prepareStatement("SELECT LAST_INSERT_ID()");
        ResultSet rs = ps.executeQuery();
        int lastInsertId = -1;
        if (rs.next()) {
            lastInsertId = rs.getInt(1);
            System.out.println("Last insertVal:" + lastInsertId);
        }
        return lastInsertId;
    }

    public boolean addDelivery(Delivery delivery, int orderId) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }
        try {
            String sqlStatement = "INSERT INTO delivery (delivered,order_id) VALUES(?,?)";
            ps = con.prepareStatement(sqlStatement);
            ps.setBoolean(1, false);
            ps.setInt(2, orderId);

            if (ps.executeUpdate() > 0) {
                retVal = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error adding to delivery: " + ex.getMessage());
        }
        return retVal;
    }

    public boolean addInvoice(Invoice invoice, int orderId) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }
        try {
            ps = con.prepareStatement("INSERT INTO invoice (isPaid, orderId) VALUES(?,?)");
            ps.setBoolean(1, true);
            ps.setInt(2, orderId);
            if (ps.executeUpdate() > 0) {
                int invoiceId = getInvoiceId();
                if (invoiceId > 0) {
                    retVal = addPayment(invoice.getPayment(), invoiceId);
                }

            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return retVal;
    }

    public boolean updateDeliveryByOrderId(int orderId) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }
        try {
            String sqlStatement = "UPDATE delivery SET delivered=?, deliveryDate=? WHERE order_Id=?";
            ps = con.prepareStatement(sqlStatement);
            ZonedDateTime zonedDateTimeOrder = LocalDateTime.now().atZone(ZoneId.systemDefault());
            Instant insOrd = zonedDateTimeOrder.toInstant();
            java.util.Date orderDate = Date.from(insOrd);
            ps.setBoolean(1, true);
            ps.setTimestamp(2, new java.sql.Timestamp(orderDate.getTime()));
            ps.setInt(3, orderId);
            if (ps.executeUpdate() > 0) {
                retVal = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error updating delivery" + ex.getMessage());
        }
        return retVal;
    }

    public boolean addPayment(Payment payment, int invoiceId) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }
        try {
            ps = con.prepareStatement("INSERT INTO payment(amount, paymentMethod, datePaid, invoiceId) VALUES(?,?,?,?)");

            ZonedDateTime zonedDateTimeOrder = LocalDateTime.now().atZone(ZoneId.systemDefault());
            Instant insOrd = zonedDateTimeOrder.toInstant();
            java.util.Date payDate = Date.from(insOrd);

            ps.setDouble(1, payment.getAmount());
            ps.setString(2, payment.getPaymentMethod().name());
            ps.setTimestamp(3, new java.sql.Timestamp(payDate.getTime()));
            ps.setInt(4, invoiceId);
            if (ps.executeUpdate() > 0) {
                retVal = true;
            }
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        return retVal;
    }

    public static void main(String[] args) {
        System.out.println(Status.values()[4].name());
        DBManager dbm = new DBManager("jdbc:mysql://localhost:3306/", "bakery?useSSL=false", "root", "root", "com.mysql.cj.jdbc.Driver");
//        List<OrderItem> items = new ArrayList();
//        ProductDaoImpl impl = ProductDaoImpl.getInstance(dbm.getConnection());
//        
//        Delivery delivery = new Delivery(null, false);
//        items.add(new OrderItem(1,5 , 20,impl.getProductById(5)));
////        items.add(new OrderItem(8, 3, 20, impl.getProductById(6)));
////        items.add(new OrderItem(9, 1, 20, impl.getProductById(7)));
//        double totAmt = 0;
//        Order ord = new Order();
//        ord.setOrderItems(items);
        // System.out.println(ord.getOrderIngredientQty());

        // System.out.println((1.0/6)*5*5);
//        System.out.println(OrderDaoImpl.getInstance(dbm.getConnection()).updateOrderStatus(11, Status.values()[1]));

//        OrderDaoImpl.getInstance(dbm.getConnection()).addOrder(new Order(4, LocalDateTime.now(), Status.PENDING, items, delivery, new Invoice(2, true, new Payment(1, PaymentMethod.PAYPAL, totAmt, LocalDateTime.now()))), 1);
//        new OrderDaoImpl(dbm.getConnection()).getAllOrders().forEach(System.out::println);
        System.out.println(new OrderDaoImpl(dbm.getConnection()).getDeliveryNoteByOrderId(2));

    }

    @Override
    public DeliveryNote getDeliveryNoteByOrderId(int num) {
        DeliveryNote note = null;

        if (con == null) {
            return note;
        }

        try {
            ps = con.prepareStatement("SELECT us.name,us.surname,o.orderDate, d.deliveryId, o.orderId,"
                    + " a.addressId,a.streetname, a.surburb, a.city, a.province,"
                    + " a.zipcode, a.country, a.isActive"
                    + " FROM user us, `order` o, delivery d, user_address ua, address a "
                    + "  WHERE a.addressId = ua.addressId "
                    + " AND us.email = ua.email "
                    + " AND o.orderId = d.order_id"
                    + " AND o.user_address_id = ua.id"
                    + " AND o.orderId = ?");
            
            ps.setInt(1, num);
//            ps.setInt(1, 1);

            ResultSet res = ps.executeQuery();
            
            note = new DeliveryNote();
            if (res.next()) {
                note.setDeliveryId(res.getInt("d.deliveryId"));
                note.setOrderId(res.getInt("o.orderId"));
                note.setUserName(res.getString("us.name"));
                note.setUserSurname(res.getString("us.surname"));
                note.setOrderDate(res.getTimestamp("o.orderDate").toLocalDateTime());
                Address addr = new Address();
                addr.setAddressId(res.getInt("a.addressId"));
                
                try {
                    addr.setStreetName(res.getString("a.streetname"));
                    addr.setSuburb(res.getString("a.surburb"));
                    addr.setCity(res.getString("a.city"));
                    addr.setProvince(res.getString("a.province"));
                    addr.setCountry(res.getString("a.country"));
                    addr.setPostalCode(res.getString("a.zipcode"));

                } catch (ArgumentException ex) {
                    System.out.println("Error: "+ex.getMessage());
                    return null;
                }

                note.setAddress(addr);

            }

        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
        }

        return note;
    }
    
    
    
    @Override
    public Order getOrderByUserIdAndInvoiceId(String email, int invoiceId) {
        Order order = null;
        if (con == null) {
            return order;
        }
        
        try {
            ps = con.prepareStatement("SELECT us.name, us.email, o.orderId, o.status, o.orderDate, d.deliveryDate, d.delivered, i.isPaid, i.invoiceId, p.paymentId, p.datePaid, p.paymentMethod, p.amount, o.totalAmount, ua.email, ua.addressId, a.addressId "
                    + " FROM user us, `order` o, delivery d, invoice i, user_address ua, address a, payment p "
                    + " WHERE us.email = ua.email "
                    + " AND a.addressId = ua.addressId "
                    + " AND o.orderId = i.orderId "
                    + " AND o.orderId = d.order_id "
                    + " AND o.user_address_Id = ua.id "
                    + " AND i.invoiceId = p.invoiceId "
                    + " AND us.email = ?"
                    + " AND i.invoiceId =? ");
            ps.setString(1, email);
            ps.setInt(2, invoiceId);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                order = new Order(rs.getInt("o.orderId"), rs.getTimestamp("o.orderDate").toLocalDateTime(), Status.valueOf(rs.getString("o.status")), rs.getDouble("o.totalAmount"), new Delivery(rs.getTimestamp("d.deliveryDate") == null ? null : rs.getTimestamp("d.deliveryDate").toLocalDateTime(), rs.getBoolean("d.delivered")), new Invoice(rs.getInt("i.invoiceId"), rs.getBoolean("i.isPaid"), new Payment(rs.getInt("p.paymentId"), PaymentMethod.valueOf(rs.getString("p.paymentMethod")) == null ? null : PaymentMethod.valueOf(rs.getString("p.paymentMethod")), rs.getDouble("p.amount"), rs.getTimestamp("p.datePaid") == null ? null :rs.getTimestamp("p.datePaid").toLocalDateTime())));
                order.setUserId(email);
                order.setOrderItems(getAllOrderItemsByOrderId(order.getOrderId()));
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        
        return order;
    }
    
    
    @Override
    public List<Order> getAllUserOrdersByUserId(String email) {
        List<Order> orderList = null;
        if (con == null) {
            return orderList;
        }
        try {
            ps = con.prepareStatement("SELECT us.name, us.email, o.orderId, o.status, o.orderDate, d.deliveryDate, d.delivered, i.isPaid, i.invoiceId, p.paymentId, p.datePaid, p.paymentMethod, p.amount, o.totalAmount, ua.email, ua.addressId, a.addressId "
                    + " FROM user us, `order` o, delivery d, invoice i, user_address ua, address a, payment p "
                    + " WHERE us.email = ua.email "
                    + " AND a.addressId = ua.addressId "
                    + " AND o.orderId = i.orderId "
                    + " AND o.orderId = d.order_id "
                    + " AND o.user_address_Id = ua.id "
                    + " AND i.invoiceId = p.invoiceId "
                    + " AND us.email = ?");
            ps.setString(1, email);
            
            ResultSet rs = ps.executeQuery();
            orderList = new ArrayList<>();
            Order order;
            while (rs.next()) {
                order = new Order(rs.getInt("o.orderId"), rs.getTimestamp("o.orderDate").toLocalDateTime(), Status.valueOf(rs.getString("o.status")), rs.getDouble("o.totalAmount"), new Delivery(rs.getTimestamp("d.deliveryDate") == null ? null : rs.getTimestamp("d.deliveryDate").toLocalDateTime(), rs.getBoolean("d.delivered")), new Invoice(rs.getInt("i.invoiceId"), rs.getBoolean("i.isPaid"), new Payment(rs.getInt("p.paymentId"), PaymentMethod.valueOf(rs.getString("p.paymentMethod")) == null ? null : PaymentMethod.valueOf(rs.getString("p.paymentMethod")), rs.getDouble("p.amount"), rs.getTimestamp("p.datePaid") == null ? null :rs.getTimestamp("p.datePaid").toLocalDateTime())));
                order.setUserId(email);
                order.setOrderItems(getAllOrderItemsByOrderId(order.getOrderId()));
                orderList.add(order);
            }
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
        return orderList;
    }

    @Override
    public Order getOrderByUserIdAndOrderId(String email, int orderId) {
        Order order = null;
        if (con == null) {
            return order;
        }
        
        try {
            ps = con.prepareStatement("SELECT us.name, us.email, o.orderId, o.status, o.orderDate, d.deliveryDate, d.delivered, i.isPaid, i.invoiceId, p.paymentId, p.datePaid, p.paymentMethod, p.amount, o.totalAmount, ua.email, ua.addressId, a.addressId "
                    + " FROM user us, `order` o, delivery d, invoice i, user_address ua, address a, payment p "
                    + " WHERE us.email = ua.email "
                    + " AND a.addressId = ua.addressId "
                    + " AND o.orderId = i.orderId "
                    + " AND o.orderId = d.order_id "
                    + " AND o.user_address_Id = ua.id "
                    + " AND i.invoiceId = p.invoiceId "
                    + " AND us.email = ?"
                    + " AND o.orderId =? ");
            ps.setString(1, email);
            ps.setInt(2, orderId);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                order = new Order(rs.getInt("o.orderId"), rs.getTimestamp("o.orderDate").toLocalDateTime(), Status.valueOf(rs.getString("o.status")), rs.getDouble("o.totalAmount"), new Delivery(rs.getTimestamp("d.deliveryDate") == null ? null : rs.getTimestamp("d.deliveryDate").toLocalDateTime(), rs.getBoolean("d.delivered")), new Invoice(rs.getInt("i.invoiceId"), rs.getBoolean("i.isPaid"), new Payment(rs.getInt("p.paymentId"), PaymentMethod.valueOf(rs.getString("p.paymentMethod")) == null ? null : PaymentMethod.valueOf(rs.getString("p.paymentMethod")), rs.getDouble("p.amount"), rs.getTimestamp("p.datePaid") == null ? null :rs.getTimestamp("p.datePaid").toLocalDateTime())));
                order.setUserId(email);
                order.setOrderItems(getAllOrderItemsByOrderId(order.getOrderId()));
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        
        return order;
    }

}
