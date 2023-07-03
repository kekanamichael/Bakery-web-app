/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.service.impl;

import com.itextpdf.html2pdf.HtmlConverter;
import com.techgiants.topiefor.exception.OutOfStockException;
import com.techgiants.topiefor.dao.IngredientDao;
import com.techgiants.topiefor.dao.OrderDao;
import com.techgiants.topiefor.dao.impl.IngredientDaoImpl;
import com.techgiants.topiefor.dao.impl.OrderDaoImpl;
import com.techgiants.topiefor.dao.impl.ProductDaoImpl;
import com.techgiants.topiefor.db.DBManager;
import com.techgiants.topiefor.exception.UserException;
import com.techgiants.topiefor.model.Address;
import com.techgiants.topiefor.model.Delivery;
import com.techgiants.topiefor.model.DeliveryNote;
import com.techgiants.topiefor.model.EmailMessage;
import com.techgiants.topiefor.model.Invoice;
import com.techgiants.topiefor.model.Order;
import com.techgiants.topiefor.model.OrderItem;
import com.techgiants.topiefor.model.Payment;
import com.techgiants.topiefor.model.QuantityUnit;
import com.techgiants.topiefor.model.Unit;
import com.techgiants.topiefor.paymentmethod.PaymentMethod;
import com.techgiants.topiefor.service.EmailService;
import com.techgiants.topiefor.service.OrderService;
import com.techgiants.topiefor.status.Status;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author VM JELE
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao dao;
    private IngredientDao ingredDao;
    private static OrderServiceImpl impl;
    private EmailService emailer;

    private OrderServiceImpl(OrderDao dao, IngredientDao ingredDao, EmailService emailer) {
        this.dao = dao;
        this.ingredDao = ingredDao;
        this.emailer = emailer;
    }

    public static OrderServiceImpl getInstance(OrderDao dao, IngredientDao ingredDao, EmailService emailer) {

        if (impl == null) {
            impl = new OrderServiceImpl(dao, ingredDao, emailer);
        }

        return impl;
    }

    @Override
    public boolean orderProducts(Order order, int addrId,String email) throws OutOfStockException {
        boolean bool = false;
        int orderId = 5;
        if (order == null) {
            return bool;
        }
        int id;
        checkIfThereAreSufficientIngredients(order.getOrderIngredientQty());
        if (bool = (id = dao.addOrder(order, addrId)) > 0) {
            minusTheIngredients(order.getOrderIngredientQty());
           Thread d =  new Thread() {
                @Override
                public void run() {
                    try {
                        emailer.sendMail(createOrderEmailMessage(order, id, email));
                        System.out.println("email sent");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            };
           
           d.start();

        }

        return bool;
    }

    @Override
    public boolean confirmDeliveryOfOrder(int orderId,String email) throws UserException {
        
        if(orderId<1||email==null|| email.isEmpty()){
            return false;
        }
        
        if(dao.getOrderByUserIdAndOrderId(email, orderId)==null)
            throw new UserException("You can only confirm Delivery of your own Orders");
        
        
        
        return dao.updateOrderStatus(orderId, Status.DELIVERED);
    }

    public static void main(String[] args) {

        DBManager dbm = new DBManager("jdbc:mysql://localhost:3306/", "bakery?useSSL=false", "root", "root", "com.mysql.cj.jdbc.Driver");
        List<OrderItem> items = new ArrayList();
        ProductDaoImpl ProdDao = ProductDaoImpl.getInstance(dbm.getConnection());
        items.add(new OrderItem(1, 2, 20, ProdDao.getProductById(5)));
        items.add(new OrderItem(1, 1, 20, ProdDao.getProductById(6)));
        items.add(new OrderItem(1, 2, 20, ProdDao.getProductById(7)));
        Order ord = new Order();

        ord.setOrderItems(items);
        ord.setInvoice(new Invoice(0, true, new Payment(0, PaymentMethod.CREDIT_CARD, ord.getTotalAmount(), LocalDateTime.now())));
        ord.setStatus(Status.PENDING);
        ord.setDelivery(new Delivery(LocalDateTime.now(), false));
        ord.setOrderDate(LocalDateTime.now());

//        try {
//            OrderServiceImpl.getInstance(OrderDaoImpl.getInstance(dbm.getConnection()),
//                    IngredientDaoImpl.getInstance(dbm.getConnection())).orderProducts(ord,1);
//        } catch (OutOfStockException ex) {
//            System.out.println("Error: "+ex.getMessage());
//        }
//        OrderServiceImpl.getInstance(OrderDaoImpl.getInstance(dbm.getConnection()),
//                IngredientDaoImpl.getInstance(dbm.getConnection())).getAllOrders().forEach(System.out::println);
//        
        OrderServiceImpl.getInstance(OrderDaoImpl.getInstance(dbm.getConnection()), IngredientDaoImpl.getInstance(dbm.getConnection()),null).getAllOrders()
                .forEach(System.out::println);

    }
    
    @Override
    public List<Order> getAllUsersOrders(String email) {
        if (email == null || email.isEmpty()) {
            return null;
        }
        return dao.getAllUserOrdersByUserId(email);
    }

    private void checkIfThereAreSufficientIngredients(HashMap<Integer, QuantityUnit> orderIngredientQty) throws OutOfStockException {
        Iterator iter = orderIngredientQty.keySet().iterator();
        int key;
        double ingredStock;
        while (iter.hasNext()) {
            key = (Integer) iter.next();
            ingredStock = ingredDao.getStockByIngredientId(key);

            if (ingredStock < orderIngredientQty.get(key).getQty()) {
                throw new OutOfStockException("Out Of Stock, Cannot Process Order\n"
                        + "ingredId = " + key + ", ingredStock= " + ingredStock + " quantity= " + orderIngredientQty.get(key));
            }

        }

    }

    private void minusTheIngredients(HashMap<Integer, QuantityUnit> orderIngredientQty) {
        Iterator iter = orderIngredientQty.keySet().iterator();
        int key;
        double ingredStock, ordQty;

        while (iter.hasNext()) {
            key = (Integer) iter.next();
            ingredStock = ingredDao.getStockByIngredientId(key);
            ordQty = orderIngredientQty.get(key).getQty();

            if (orderIngredientQty.get(key).getUnit() == Unit.UNIT || orderIngredientQty.get(key).getUnit() == Unit.DOZEN) {
                ordQty = Math.ceil(ordQty);
            }
            ingredStock -= ordQty;
            ingredDao.updateStockByIngredientId(key, ingredStock);

        }
    }

    @Override
    public List<Order> getAllOrders() {
        return dao.getAllOrders();
    }
    

    @Override
    public boolean updateStatus(int orderId, Status status) throws ArithmeticException {
        if(orderId<1)
            throw new ArithmeticException("Invalid values");
        
        return dao.updateOrderStatus(orderId, status);
    }

    @Override
    public void prepareDeliveryNote(HttpServletResponse response, int orderId) throws IOException {
        String document = prepareDeliveryNoteHTMLDocument(dao.getDeliveryNoteByOrderId(orderId));
        HtmlConverter.convertToPdf(document, response.getOutputStream());
        
    }

    private String prepareDeliveryNoteHTMLDocument(DeliveryNote deliveryNoteByOrderId) {
        StringBuilder builder= new StringBuilder();
        Address addr = deliveryNoteByOrderId.getAddress();
        DecimalFormat df = new DecimalFormat("00000");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY/mm/dd HH:mm:ss");
        
        builder.append("<html><head></head><body>")
                .append("<h1>Delivery Note</h1>")
                .append("<p><span>Ship to:</span> <br>")
                .append(addr.getStreetName())
                .append(", ")
                .append(addr.getSuburb())
                .append("<br> ")
                .append(addr.getCity())
                .append(", ")
                .append(addr.getProvince())
                .append("<br> ")
                .append(addr.getCountry())
                .append(", ")
                .append(addr.getPostalCode())
                .append("</p>")
                .append("<p>Delivery No#: <span>#")
                .append(df.format(deliveryNoteByOrderId.getDeliveryId()))
                .append("</span></p>")
                .append("<p>Order No#: <span>#")
                .append(df.format(deliveryNoteByOrderId.getOrderId()))
                .append("</span> Date: ")
                .append(dtf.format(deliveryNoteByOrderId.getOrderDate()))
                .append("</p>")
                .append("")
                .append("<p>Client's Info: <span><i>")
                .append(deliveryNoteByOrderId.getUserName())
                .append(" ")
                .append(deliveryNoteByOrderId.getUserSurname())
                .append("</span></i><p>")
                .append("<p>Please allow the customer to click the following link <a href=\"localhost:8080/BakeryWebApp/service?service=client&process=confirm-delivery&orderId=")
                .append(deliveryNoteByOrderId.getOrderId())
                .append("\">here")
                .append("</a>")
                .append("")
                .append("</body></html>");
        
        
        
        return builder.toString();
    }
    
    
    @Override
    public Order getOrderByUserIdAndInvoiceId(String email, int invoiceId) {
        if (email == null || email.isEmpty() || invoiceId < 1) {
            return null;
        }

        return dao.getOrderByUserIdAndInvoiceId(email, invoiceId);
    }

    @Override
    public void prepareOrderInvoice(HttpServletResponse response, Order order) throws IOException {
        String orderDoc = prepareOrderInvoiceHTMLDocument(order);
        HtmlConverter.convertToPdf(orderDoc, response.getOutputStream());
        
        
    }

    private String prepareOrderInvoiceHTMLDocument(Order order) {
         StringBuilder builder = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#,###.00");

        
        builder.append("<html><head><style>")
                .append(" td{ width:33%; }")
                .append(" h1{ text-align:center; font-size:50px }")
                .append(".brand{ text-align: center;}")
                .append("</style></head><body>")
                .append("<h1>Invoice</h1>")
                .append("<p class=\"brand\">ToPieFor@TechGiant's</p>")
                .append("<p>Invoice No. <strong>#")
                .append(new DecimalFormat("00000").format(order.getInvoice().getInvoiceId()))
                .append("</strong></p>")
                .append("<p>Date")
                .append(order.getInvoice().getPayment().getDatePaid().toString())
                .append("</p>")
                .append("");

        builder.append("<p>Order Items: </p>")
                .append("<table style=\"width:100%;height:fit-content;margin:20px\">");
        for (OrderItem i : order.getOrderItems()) {
            builder.append("<tr>")
                    .append("<td>")
                    .append(i.getProduct().getName())
                    .append("</td>")
                    .append("<td>")
                    .append(" X ")
                    .append(i.getQuantity())
                    .append("</td>")
                    .append("<td>")
                    .append(" R ")
                    .append(df.format(i.getUnitPrice()))
                    .append("</td>");

            builder.append("</tr>");
        }
        builder.append("<tr>")
                .append("<td>")
                .append("Total cost:")
                .append("</td>")
                .append("<td></td>")
                .append("<td>")
                .append("<hr style=\"margin-right:70%\">R ")
                .append(df.format(order.getTotalAmount()))
                .append("<td>")
                .append("</tr>");
        builder.append("</table>");

        builder
                .append("<p></p>")
                .append("<p class=\"brand\" style\"font-size:10px\">copyright &copy;</p>");
        builder.append("</body><html>");
        return builder.toString();
    }
    
    private EmailMessage createOrderEmailMessage(Order order, int orderId, String email) {

        String fOrderId = "#" + new DecimalFormat("00000").format(orderId);
        EmailMessage emialer = new EmailMessage();
        emialer.setMessageType("text/html");
        emialer.setReceiver(email);
        emialer.setSubject("ToPieFor Order" + fOrderId + " detailes");
        emialer.setTitle("No-Reply ToPieFor");

        emialer.setMessage(formartEmailMessage(order, fOrderId));

        return emialer;
    }

    private static String formartEmailMessage(Order order, String fOrderId) {
        StringBuilder builder = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#,###.00");

        builder.append("<html><head><style>")
                .append(" td{ width:50%; }")
                .append("</style></head><body>")
                .append("")
                .append("")
                .append("<p>Dear customer,</p>")
                .append("<p>This is the summary of the Order Details:</p>")
                .append("<p>Order <strong>")
                .append(fOrderId)
                .append("</strong></p>")
                .append("");

        builder.append("<p>Order Items: </p>")
                .append("<table style=\"width:100%;height:fit-content;margin:20px\">");
        for (OrderItem i : order.getOrderItems()) {
            builder.append("<tr>")
                    .append("<td>")
                    .append(i.getProduct().getName())
                    .append(" X ")
                    .append(i.getQuantity())
                    .append("</td>")
                    .append("<td>")
                    .append("R ")
                    .append(df.format(i.getUnitPrice()))
                    .append("</td>");

            builder.append("</tr>");
        }
        builder.append("<tr>")
                .append("<td>")
                .append("Total cost:")
                .append("</td>")
                .append("<td>")
                .append("<hr style=\"margin-right:70%\">R ")
                .append(df.format(order.getTotalAmount()))
                .append("<td>")
                .append("</tr>");
        builder.append("</table>");

        builder
                .append("<p>Kind Regards,</p>")
                .append("<p>ToPieFor@TechGiant's</p>");
        builder.append("</body><html>");
        return builder.toString();
    }
    
    


}
