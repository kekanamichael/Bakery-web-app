package com.techgiants.topiefor.process;

import com.techgiants.topiefor.dao.impl.AddressDaoImpl;
import com.techgiants.topiefor.dao.impl.IngredientDaoImpl;
import com.techgiants.topiefor.dao.impl.OrderDaoImpl;
import com.techgiants.topiefor.dao.impl.ProductDaoImpl;
import com.techgiants.topiefor.dao.impl.RoleDaoImpl;
import com.techgiants.topiefor.dao.impl.UserDaoImpl;
import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.exception.OutOfStockException;
import com.techgiants.topiefor.exception.UserException;
import com.techgiants.topiefor.model.Address;
import com.techgiants.topiefor.model.Delivery;
import com.techgiants.topiefor.model.Invoice;
import com.techgiants.topiefor.model.Order;
import com.techgiants.topiefor.model.OrderItem;
import com.techgiants.topiefor.model.Payment;
import com.techgiants.topiefor.model.User;
import com.techgiants.topiefor.paymentmethod.PaymentMethod;
import com.techgiants.topiefor.service.EmailService;
import com.techgiants.topiefor.service.impl.OrderServiceImpl;
import com.techgiants.topiefor.service.impl.ProductServiceImpl;
import com.techgiants.topiefor.service.impl.RoleServiceImpl;
import com.techgiants.topiefor.service.impl.UserServiceImpl;
import com.techgiants.topiefor.status.Status;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClientRequest extends ProcessRequest {

    private User user;
    private HttpSession session = null;

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        super.processTheRequest(request, response);

        session = request.getSession();
        // if ((user = (User) request.getSession().getAttribute("user")) == null) {
        if ((user = (User) session.getAttribute("user")) == null) {
            msg = "Please Login First";
            viewPage = "login.jsp";
            return;
        }

        switch (process) {
            case "logout":
                clearSession(request, response);
                break;
            case "confirm-delivery":
                confirmDelivery(request, response);
                break;
            case "add-new-address":
                addNewAddress(request, response);
                break;
            case "adding-address":
                addDeliveryAddress(request, response);
                break;
            case "all-orders":
                returnAllCustomerOrders(request, response);
                break;
            case "get-invoice":
                prepareOrderInvoice(request, response);
                break;
            case "all-addresses":
                break;
            case "check-out":
                checkingOut(request, response);
                break;
            case "process-order":
                processOrder(request, response);
                break;
            case "ordering-products":
                break;

        }
    }

    private void submitOrderOfProduct(HttpServletRequest request, HttpServletResponse response) {

    }

    private void checkingOut(HttpServletRequest request, HttpServletResponse response) {
        HashMap<Integer, OrderItem> items = (HashMap<Integer, OrderItem>) request.getSession().getAttribute("cart");

        if (items == null || items.isEmpty()) {
            msg = "Your Cart is Empty";
            viewPage = "card1.jsp";

        } else {
            roleServ = new RoleServiceImpl(new RoleDaoImpl(dbm.getConnection()));
            addrDao = AddressDaoImpl.getInstance(dbm.getConnection());
            emailer = (EmailService) request.getServletContext().getAttribute("email-service");
            userServ = new UserServiceImpl(new UserDaoImpl(dbm.getConnection()), roleServ, addrDao,emailer);
//            request.getSession().setAttribute("addresses", userServ.getUserAddressesByEmail(user.getEmail()));
            session.setAttribute("addresses", userServ.getUserAddressesByEmail(user.getEmail()));
            msg = null;
            viewPage = "choose-address.jsp";
        }

    }

    private void addNewAddress(HttpServletRequest request, HttpServletResponse response) {
        userDao = UserDaoImpl.getInstance(dbm.getConnection());
        addrDao = AddressDaoImpl.getInstance(dbm.getConnection());
        roleServ = RoleServiceImpl.getInstance(RoleDaoImpl.getInstance(dbm.getConnection()));
        emailer = (EmailService) request.getServletContext().getAttribute("email-service");
        userServ = UserServiceImpl.getInstance(userDao, roleServ, addrDao,emailer);
        viewPage = "choose-address.jsp";

        Address addr = new Address();
        String strName = request.getParameter("strName");
        String suburb = request.getParameter("suburb");
        String zipCode = request.getParameter("zipcode");
        String country = request.getParameter("country");
        String province = request.getParameter("province");
        String city = request.getParameter("city");

        try {
            addr.setStreetName(strName);
            addr.setCity(city);
            addr.setPostalCode(zipCode);
            addr.setSuburb(suburb);
            addr.setCountry(country);
            addr.setProvince(province);
        } catch (ArgumentException ex) {
            msg = "Please Enter Valid Address";

            //request.getSession().setAttribute("addresses", userServ.getUserAddressesByEmail(user.getEmail()));
            session.setAttribute("addresses", userServ.getUserAddressesByEmail(user.getEmail()));
            return;
        }

        if (userServ.AddNewAddress(addr, user.getEmail(), 'c')) {
            msg = "New Address Added successfully";
        } else {
            msg = "Failed To Add new Address";
        }
        // request.getSession().setAttribute("addresses", userServ.getUserAddressesByEmail(user.getEmail()));
        session.setAttribute("addresses", userServ.getUserAddressesByEmail(user.getEmail()));
    }

    private void addDeliveryAddress(HttpServletRequest request, HttpServletResponse response) {
        userDao = UserDaoImpl.getInstance(dbm.getConnection());
        addrDao = AddressDaoImpl.getInstance(dbm.getConnection());
        emailer = (EmailService) request.getServletContext().getAttribute("email-service");
        roleServ = RoleServiceImpl.getInstance(RoleDaoImpl.getInstance(dbm.getConnection()));
        userServ = UserServiceImpl.getInstance(userDao, roleServ, addrDao,emailer);
        String addrIdStr = request.getParameter("user-address-id");

        int addrId;

        try {
            addrId = Integer.parseInt(addrIdStr);
            //request.getSession().setAttribute("addrId", addrId);
            session.setAttribute("addrId", addrId);
            viewPage = "pay.jsp";
        } catch (NumberFormatException numberFormatException) {
            msg = "Please Choose Valid Address";
            viewPage = "choose-address.jsp";
//            request.getSession().setAttribute("addresses", userServ.getUserAddressesByEmail(user.getEmail()));
            session.setAttribute("addresses", userServ.getUserAddressesByEmail(user.getEmail()));

        }

    }

    private void processOrder(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        HashMap<Integer, OrderItem> items = (HashMap<Integer, OrderItem>) session.getAttribute("cart");
        //    int addrId = (Integer) session.getAttribute("addrId");
        int addrId = (Integer) session.getAttribute("addrId");
        if (items == null || items.isEmpty()) {
            msg = "Your Cart is Empty";
            viewPage = "card1.jsp";
            return;
        }

        if (addrId < 1) {
            msg = "Invalid Delivery Address for the Order";
            viewPage = "card1.jsp";
            return;
        }

        Order ord = new Order();

        ord.setOrderItems(new ArrayList<>(items.values()));
        ord.setInvoice(new Invoice(0, true, new Payment(0, PaymentMethod.CREDIT_CARD, ord.getTotalAmount(), LocalDateTime.now())));
        ord.setStatus(Status.PENDING);
        ord.setDelivery(new Delivery(LocalDateTime.now(), false));
        ord.setOrderDate(LocalDateTime.now());
        msg = "Order Submitted";
        emailer = (EmailService) request.getServletContext().getAttribute("email-service");
        orderServ = OrderServiceImpl.getInstance(OrderDaoImpl.getInstance(dbm.getConnection()), IngredientDaoImpl.getInstance(dbm.getConnection()), emailer);
        try {
            if (orderServ.orderProducts(ord, addrId, user.getEmail())) {
                session.setAttribute("cart", new HashMap<Integer, OrderItem>());
                msg = "Order Successfully Placed Please check emails for more infor on your Order";
            }
        } catch (OutOfStockException ex) {
            msg = ex.getMessage();

        }
        proServ = ProductServiceImpl.getInstance(ProductDaoImpl.getInstance(dbm.getConnection()));

        viewPage = "card1.jsp";
        request.setAttribute("prods", proServ.getAllProducts());
    }

    private void clearSession(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        viewPage = "login.jsp";
        msg = "Successfully Logged out";
    }

    private void returnAllCustomerOrders(HttpServletRequest request, HttpServletResponse response) {
        orderServ = OrderServiceImpl.getInstance(OrderDaoImpl.getInstance(dbm.getConnection()), IngredientDaoImpl.getInstance(dbm.getConnection()), null);
        viewPage = "myOrders.jsp";
        List<Order> orders = orderServ.getAllUsersOrders(user.getEmail());
        request.setAttribute("my-orders", orders);

    }

    private void prepareOrderInvoice(HttpServletRequest request, HttpServletResponse response) {
        returnAllCustomerOrders(request, response);
        int invoiceId;

        try {
            invoiceId = Integer.parseInt(request.getParameter("invoice-id"));
            Order ord = orderServ.getOrderByUserIdAndInvoiceId(user.getEmail(), invoiceId);

            if (ord == null) {
                msg = "Invoice Id/this is not your invoice";
                return;
            }
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"invoice-" + ord.getInvoice().getInvoiceId() + ".pdf\"");

            try {
                orderServ.prepareOrderInvoice(response, ord);
            } catch (IOException ex) {
                msg = "Error occured: could not Download invoice";
            }

        } catch (NumberFormatException numberFormatException) {
            msg = "Invalid invoice Number";
        }

    }

    private void confirmDelivery(HttpServletRequest request, HttpServletResponse response) {
        orderServ = OrderServiceImpl.getInstance(OrderDaoImpl.getInstance(dbm.getConnection()), IngredientDaoImpl.getInstance(dbm.getConnection()), null);
        
        
        viewPage = "index.jsp";
        int orderId;
        
        try {
            orderId = Integer.parseInt(request.getParameter("orderId"));
            if(orderServ.confirmDeliveryOfOrder(orderId,user.getEmail())){
                
            }
            
        
        } catch (NumberFormatException numberFormatException) {
            msg="Cannot confirm Delivery with invalid orderId";
            
        } catch (UserException ex) {
            msg = ex.getMessage();
        }
        
        
    }

}
