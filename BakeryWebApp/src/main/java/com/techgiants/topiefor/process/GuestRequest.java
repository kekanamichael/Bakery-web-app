package com.techgiants.topiefor.process;

import com.techgiants.topiefor.dao.impl.AddressDaoImpl;
import com.techgiants.topiefor.dao.impl.ProductDaoImpl;
import com.techgiants.topiefor.dao.impl.RoleDaoImpl;
import com.techgiants.topiefor.dao.impl.UserDaoImpl;
import com.techgiants.topiefor.emailer.EmailServiceImpl;
import com.techgiants.topiefor.exception.UserException;
import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.exception.PasswordException;
import com.techgiants.topiefor.model.Address;
import com.techgiants.topiefor.model.EmailMessage;
import com.techgiants.topiefor.model.OrderItem;
import com.techgiants.topiefor.model.Product;
import com.techgiants.topiefor.model.User;
import com.techgiants.topiefor.service.EmailService;
import com.techgiants.topiefor.service.impl.ProductServiceImpl;
import com.techgiants.topiefor.service.impl.RoleServiceImpl;
import com.techgiants.topiefor.service.impl.UserServiceImpl;
import java.util.HashMap;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuestRequest extends ProcessRequest {

    

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        super.processTheRequest(request, response);

        if (process == null || process.isEmpty()) {
            return;
        }

        switch (process) {
            case "create-account":
                createUser(request, response);
                break;
            case "complete-registration":
                completeRegistration(request, response);
                break;
            case "view-products":
                returnAllTheProducts(request, response);
                break;
            case "update-cart":
                updateCart(request, response);
                break;
            case "clear-cart":
                
                proServ = ProductServiceImpl.getInstance(ProductDaoImpl.getInstance(dbm.getConnection()));
                request.setAttribute("prods", proServ.getAllProducts());
                request.getSession().setAttribute("cart", new HashMap<Integer,OrderItem>());
                viewPage = "card1.jsp";
                msg="Cart cleared";
                break;
            case "login":
                authenticateUser(request, response);
                break;
            
        }

    }

    private void returnAllTheProducts(HttpServletRequest request, HttpServletResponse response) {
        proServ = ProductServiceImpl.getInstance(ProductDaoImpl.getInstance(dbm.getConnection()));

        viewPage = "card1.jsp";
        request.setAttribute("prods", proServ.getAllActiveProducts());
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) {
        roleServ = new RoleServiceImpl(new RoleDaoImpl(dbm.getConnection()));
        addrDao = AddressDaoImpl.getInstance(dbm.getConnection());
        userServ = UserServiceImpl.getInstance(new UserDaoImpl(dbm.getConnection()), roleServ, addrDao,null);

        User user = new User();
        Address addr = new Address();
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String mobileNum = request.getParameter("mobileNum");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String strName = request.getParameter("strName");
        String suburb = request.getParameter("suburb");
        String zipCode = request.getParameter("zipCode");
        String country = request.getParameter("country");
        String city = request.getParameter("city");

        try {
            user.setName(name);
            user.setEmail(email);
            user.setSurname(lastname);
            user.setMobileNum(mobileNum);
            user.setPassword(password);
            user.setRole(roleServ.getRoleById(2));

            //Set Address Field
            addr.setStreetName(strName);
            addr.setCity(city);
            addr.setPostalCode(zipCode);
            addr.setSuburb(suburb);
            addr.setCountry(country);
            addr.setProvince("Gauteng");

            user.setAddress(addr);

            emailer = (EmailService) request.getServletContext().getAttribute("email-service");

            final Random rand = new Random();
            int randNum = rand.nextInt(9999) + 1101;
            System.out.println("Code: " + randNum);

            try {

                session.setAttribute("user", user);
                session.setAttribute("num", randNum);
                viewPage = "code.jsp";
                emailer.sendMail(generateVerificationEmail(randNum, user.getEmail()));
            } catch (Exception e) {
                System.out.println("Error Occurred");
            }

        } catch (ArgumentException ex) {
            viewPage = "sign-up.jsp";
            msg = "Failed to Register: " + ex.getMessage();
        }
    }

    private void authenticateUser(HttpServletRequest request, HttpServletResponse response) {

        userDao = UserDaoImpl.getInstance(dbm.getConnection());
        addrDao = AddressDaoImpl.getInstance(dbm.getConnection());
        roleServ = RoleServiceImpl.getInstance(RoleDaoImpl.getInstance(dbm.getConnection()));
        userServ = UserServiceImpl.getInstance(userDao, roleServ, addrDao,null);

        String username = request.getParameter("email");
        String password = request.getParameter("password");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("msg", "username/password is null");
            return;
        }

        User user;

        try {

            user = userServ.authenticateUserLogin(username, password);

            if (user != null) {
                request.getSession().setAttribute("user", user);
                viewPage = "index.jsp";
                msg = "Successfully Logged In";
            } else {
                viewPage = "login.jsp";
                msg = "Invalid Credentials";
            }
        } catch (UserException | ArgumentException ex) {
            viewPage = "login.jsp";
            msg = "Error: " + ex.getMessage();
        }
    }

    private void updateCart(HttpServletRequest request, HttpServletResponse response) {
        String subProcess = request.getParameter("sub-process");
        HashMap<Integer, OrderItem> items = (HashMap<Integer, OrderItem>) request.getSession().getAttribute("cart");
        proServ = ProductServiceImpl.getInstance(ProductDaoImpl.getInstance(dbm.getConnection()));
        if (items == null) {
            items = new HashMap<>();

        }
        int prodId;
        try {
            prodId = Integer.parseInt(request.getParameter("prod-id"));
        } catch (NumberFormatException numberFormatException) {
            msg = "Invalid Process request";
            viewPage = "view-404.jsp";
            return;
        }

        if (subProcess == null) {
            msg = "Invalid Process request";
            viewPage = "view-404.jsp";
            return;
        }
        switch (subProcess) {
            case "add-new-item":
                addNewItem(request, response, items,prodId);
                break;
            case "remove-item":
                items.remove(prodId);
                viewPage = "card1.jsp";
                break;

            case "update-item-qty":
                updateIngredientQty(request, response, items,prodId);
                break;

        }
        request.getSession().setAttribute("cart", items);
        request.setAttribute("prods", proServ.getAllProducts());
    }

   

    private void addNewItem(HttpServletRequest request, HttpServletResponse response, HashMap<Integer, OrderItem> items,int prodId) {
      

        if (!items.isEmpty() && items.get(prodId) != null) {
            msg = "Product Already Added to Cart";
            viewPage = "card1.jsp";
            return;
        }
        
        Product prod = proServ.getProductById(prodId);
        items.put(prodId, new OrderItem(0, 1, prod.getUnitPrice(), prod));
        msg = "Product Added Successfully";
        viewPage = "card1.jsp";
    }

    private void updateIngredientQty(HttpServletRequest request, HttpServletResponse response,
            HashMap<Integer, OrderItem> items,int prodId) {
        if (items.isEmpty()) {
            msg = "Cart is Empty";
            viewPage = "card1.jsp";
            return;
        }
        char op;
        try {
            prodId = Integer.parseInt(request.getParameter("prod-id"));

        } catch (NumberFormatException numberFormatException) {
            msg = "Invalid Process request";
            viewPage = "view-404.jsp";
            return;
        }

        if ((op = request.getParameter("operation").charAt(0)) != '+' && op != '-') {
            msg = "Invalid operation";
            return;
        }
        
        viewPage = "card1.jsp";
        OrderItem item = items.get(prodId);

        if (op == '+') {
            item.setQuantity(item.getQuantity() + 1);

        } else {
            item.setQuantity(item.getQuantity() - 1);

        }

        if (item.getQuantity() < 1) {
            items.remove(prodId);
            msg= "Product Removed from the Cart";
        }else{
            items.put(prodId, item);
                
        }
    }
    
    
    private EmailMessage generateVerificationEmail(int randNum, String email) {

        StringBuilder builder = new StringBuilder();
        builder.append("Dear customer,\n\n")
                .append("We would like you to verify your email by entering the the Following Code:\n")
                .append("c")
                .append(randNum)
                .append("\n\n")
                .append("Kind regards,")
                .append("ToPieFor@Techgiants");
        EmailMessage msgEm = new EmailMessage();
        msgEm.setMessageType("text/html");
        msgEm.setReceiver(email);
        msgEm.setSubject("Verification Code");
        msgEm.setMessage(builder.toString());
        msgEm.setTitle("No-reply topiefor");

        return msgEm;
    }

    private void completeRegistration(HttpServletRequest request, HttpServletResponse response) {
        roleServ = new RoleServiceImpl(new RoleDaoImpl(dbm.getConnection()));
        addrDao = AddressDaoImpl.getInstance(dbm.getConnection());
        emailer = (EmailService) request.getServletContext().getAttribute("email-service");
        userServ = UserServiceImpl.getInstance(new UserDaoImpl(dbm.getConnection()), roleServ, addrDao,emailer);

        User user = (User) session.getAttribute("user");
        int num = (Integer) session.getAttribute("num");

        if (user == null || num < 1) {
            msg = "Please sign Up first";
            viewPage = "sign-up.jsp";
            return;
        }

        viewPage = "code.jsp";
        msg = "Invalid Code!";
        String userCode = request.getParameter("v-code");
        int code;

        try {
            code = Integer.parseInt(userCode);
        } catch (NumberFormatException numberFormatException) {
            return;
        }

        if (code != num) {
            return;
        }

        try {
            if (userServ.registerUser(user)) {
                msg = user.getName() + " You have Successfully Registered";
                viewPage = "login.jsp";

            } else {
                viewPage = "sign-up.jsp";
                msg = "Failed To Register Account";
            }
        } catch (ArgumentException | PasswordException passwordException) {
        }

    }

    

}
