package com.techgiants.topiefor.dao.impl;

import com.techgiants.topiefor.dao.Ingredient_OrderDao;
import com.techgiants.topiefor.db.DBManager;
import com.techgiants.topiefor.model.Ingredient;
import com.techgiants.topiefor.model.Ingredient_Order;
import com.techgiants.topiefor.model.Ingredient_OrderItem;
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

public class Ingredient_OrderDaoImpl implements Ingredient_OrderDao {

    private static Ingredient_OrderDaoImpl ingredient_OrderDaoImpl;
    private Connection con = null;
    private PreparedStatement ps;

    private Ingredient_OrderDaoImpl(Connection con) {
        this.con = con;
    }

    public static Ingredient_OrderDaoImpl getInstance(Connection con) {
        if (ingredient_OrderDaoImpl == null) {
            ingredient_OrderDaoImpl = new Ingredient_OrderDaoImpl(con);
        }
        return ingredient_OrderDaoImpl;
    }

    @Override
    public List<Ingredient_Order> getAllIngredientsOrder() {
        List<Ingredient_Order> orderList = new ArrayList();
        if (con == null) {
            return orderList;
        }
        try {
            ps = con.prepareStatement("SELECT id, order_date, delivery_date, delivered FROM Ingredient_Order");
            ResultSet rs = ps.executeQuery();
            Ingredient_Order order;
            while (rs.next()) {
                order = new Ingredient_Order(rs.getInt("id"), rs.getTimestamp("delivery_date") == null ? null : rs.getTimestamp("delivery_date").toLocalDateTime(), rs.getTimestamp("order_date").toLocalDateTime(), rs.getBoolean("delivered"));
                order.setOrderItems(getAllOrderItemsByOrderId(order.getId()));
                orderList.add(order);
            }
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
        return orderList;
    }

    @Override
    public boolean addIngredient_Order(Ingredient_Order order) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }
        try {
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO Ingredient_Order (order_date, delivered) VALUES(?,?)");

            ZonedDateTime zonedDateTimeOrder = LocalDateTime.now().atZone(ZoneId.systemDefault());
            Instant insOrd = zonedDateTimeOrder.toInstant();
            java.util.Date orderDate = Date.from(insOrd);

            ps.setTimestamp(1, new java.sql.Timestamp(orderDate.getTime()));
            ps.setBoolean(2, false);

            if (ps.executeUpdate() > 0) {
                int orderId = getOrderId();

                if (orderId > 0) {
                    retVal = true;
                    int cnt = 0;
                    List<Ingredient_OrderItem> items = order.getOrderItems();

                    while (cnt < items.size() && retVal) {

                        retVal = addOrderItem(items.get(cnt), orderId);
                        cnt++;
                    }
                }

                if (retVal) {
                    con.commit();
                } else {
                    con.rollback();
                }

            }
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("SQL Error: " + ex.getMessage());
            }
            System.out.println("Cannot3 add Ingredient: " + e.getMessage());
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("SQL Error: " + ex.getMessage());
            }
        }

        return retVal;
    }

    @Override
    public boolean updateIngredient_Order(int ingredient_OrderId) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }
        try {
            ps = con.prepareStatement("UPDATE Ingredient_Order SET delivery_date=?, delivered=? WHERE id = ?");

            ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.systemDefault());
            Instant ins = zonedDateTime.toInstant();
            java.util.Date deliveryDate = Date.from(ins);

            ps.setTimestamp(1, new java.sql.Timestamp(deliveryDate.getTime()));
            ps.setBoolean(2, true);
            ps.setInt(3, ingredient_OrderId);

            retVal = (ps.executeUpdate() > 0);

        } catch (SQLException e) {

            System.out.println("SQL Error first: " + e.getMessage());
        }

        return retVal;
    }

    public boolean updateIngredientStock(int ingredientId, double qty) throws SQLException {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }

        ps = con.prepareStatement("SELECT stock FROM ingredient WHERE ingredientId=?");
        ps.setInt(1, ingredientId);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {

            qty += rs.getInt("stock");
            System.out.println("stock" + qty);
            PreparedStatement ps1 = con.prepareStatement("UPDATE INGREDIENT SET stock=? WHERE ingredientId = ?");
            ps1.setDouble(1, qty);
            ps1.setInt(2, ingredientId);
            if (ps1.executeUpdate() > 0) {
                retVal = true;
            }

        } else {
            throw new SQLException();
        }

        return retVal;
    }

    @Override
    public boolean addOrderItem(Ingredient_OrderItem ingre, int ingredientOrderId) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }

        try {
            ps = con.prepareStatement("INSERT INTO Ingredient_OrderItem (ingredientId, ingredient_orderId, quantity) VALUES(?,?,?)");
            ps.setInt(1, ingre.getIngredient().getIngredientId());
            ps.setInt(2, ingredientOrderId);
            ps.setDouble(3, ingre.getQuantity());
            if (ps.executeUpdate() > 0) {
                retVal = true;
            }
        } catch (SQLException e) {
            System.out.println("Cannot add Ingredient: " + e.getMessage());
        }

        return retVal;
    }

    @Override
    public List<Ingredient_OrderItem> getAllOrderItemsByOrderId(int ingredientOrderId) {
        List<Ingredient_OrderItem> itemList = new ArrayList();
        if (con == null) {
            return itemList;
        }
        Ingredient ingred;
        try {

            ps = con.prepareStatement("SELECT oi.itemId, oi.ingredientId, oi.quantity, ing.name FROM ingredient_orderitem as oi, ingredient as ing WHERE oi.ingredient_orderId=? AND oi.ingredientId=ing.ingredientId");
            ps.setInt(1, ingredientOrderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ingred = new Ingredient(rs.getInt("oi.ingredientId"), rs.getString("ing.name"));

                itemList.add(new Ingredient_OrderItem(rs.getInt("oi.itemId"), rs.getDouble("oi.quantity"), ingred));
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

    public static void main(String[] args) {

        DBManager dbm = new DBManager("jdbc:mysql://localhost:3306/", "bakery?useSSL=false", "mie", "mie", "com.mysql.cj.jdbc.Driver");
        dbm.getConnection();
        //new Ingredient_OrderDaoImpl(dbm.getConnection()).addOrderItem(new Ingredient_OrderItem(4,10,new Ingredient(3,"floor",10,20,true,"kg")), 0);
        //List<Ingredient_OrderItem> items = new ArrayList();

//        items.add(new Ingredient_OrderItem(7, 25, new Ingredient(3, "floor")));
//        items.add(new Ingredient_OrderItem(8, 12, new Ingredient(1, "egg")));
//        items.add(new Ingredient_OrderItem(9, 15, new Ingredient(2, "Sugar")));
//
//        new Ingredient_OrderDaoImpl(dbm.getConnection()).addIngredient_Order(new Ingredient_Order(4, null, LocalDateTime.now(), false, items));
//        System.out.println(Ingredient_OrderDaoImpl.getInstance(dbm.getConnection()).getAllIngredientsOrder());
        //updating stock
        Ingredient_OrderDaoImpl.getInstance(dbm.getConnection()).updateIngredient_Order(7);

    }

    @Override
    public boolean isOrderDelivered(int orderId) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }

        try {
            ps = con.prepareStatement("SELECT delivered FROM ingredient_order WHERE id=?");

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                retVal = rs.getBoolean("delivered");

            }

        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
        }

        return retVal;

    }
}
