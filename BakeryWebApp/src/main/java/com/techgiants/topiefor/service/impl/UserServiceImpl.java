package com.techgiants.topiefor.service.impl;

import com.techgiants.topiefor.dao.AddressDao;
import com.techgiants.topiefor.dao.UserDao;
import com.techgiants.topiefor.dao.impl.AddressDaoImpl;
import com.techgiants.topiefor.dao.impl.RoleDaoImpl;
import com.techgiants.topiefor.dao.impl.UserDaoImpl;
import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Role;
import com.techgiants.topiefor.model.User;
import com.techgiants.topiefor.service.RoleService;
import com.techgiants.topiefor.service.UserService;
import com.techgiants.topiefor.db.DBManager;
import com.techgiants.topiefor.emailer.EmailServiceImpl;
import com.techgiants.topiefor.exception.PasswordException;
import com.techgiants.topiefor.exception.UserException;
import com.techgiants.topiefor.model.Address;
import com.techgiants.topiefor.model.EmailMessage;
import com.techgiants.topiefor.service.EmailService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author STUDIO 18
 */
public class UserServiceImpl implements UserService {

    private UserDao dao;
    private RoleService rServ;
    private AddressDao addrDao;
    private static UserServiceImpl service;
    private EmailService emailer;
    
    private UserServiceImpl(UserDao dao, AddressDao addr, EmailService emailer) {
        this.addrDao = addr;
        this.dao = dao;
        this.emailer = emailer;
    }

    public UserServiceImpl(UserDao dao, RoleService rServ, AddressDao addrDao, EmailService emailer) {
        this.dao = dao;
        this.rServ = rServ;
        this.addrDao = addrDao;
    }

    public static UserServiceImpl getInstance(UserDao dao, RoleService rServ, AddressDao addrDao, EmailService emailer) {
        if (service == null) {
            service = new UserServiceImpl(dao, rServ, addrDao, emailer);
        }

        return service;
    }

    @Override
    public User getUserByEmail(String email) throws ArgumentException {
        if (email == null || email.isEmpty()) {
            throw new ArgumentException("Invalid user Email");
        }

        return dao.getUserByEmail(email);
    }

    @Override
    public boolean registerUser(User user) throws ArgumentException, PasswordException {
        boolean bool = false;

        if (user == null) {
            throw new ArgumentException("Invalid User: cannot be null");
        }

        if (!checkIfUserRoleExist(user.getRole())) {
            throw new ArgumentException("Invalid User: role does not exists");
        }
        try {
            user.setPassword(encryptPassword(user.getPassword()));
        } catch (NoSuchAlgorithmException ex) {
            throw new PasswordException("Failed to Hash password");
        }

        //add user personal details
        if (!dao.addUser(user)) {
            return bool;
        }

        System.out.println("User Info Added");

        int addrId;

        //getting the Id of the address
        if ((addrId = addrDao.checkIfAddressExists(user.getAddress())) == 0) {
            addrId = addrDao.addAddress(user.getAddress());

        }

        //System.out.println("Got the Address Id: "+addrId);
        //validate address Id
        if (addrId < 0) {
            return bool;
        }

        System.out.println("Address Id Valid");

        return dao.addUserAddress(user.getEmail(), addrId, 'r');

    }

    @Override
    public boolean updateUser(User user) throws ArgumentException {
        if (user == null) {
            throw new ArgumentException("Invalid User");
        }

        if (checkIfUserRoleExist(user.getRole())) {
            throw new ArgumentException("Invalid User");
        }

        return dao.addUser(user);
    }

    @Override
    public boolean deleteUserByEmail(String email) throws ArgumentException {

        if (email == null || email.isEmpty()) {
            throw new ArgumentException("Invalid User");
        }

        return dao.deleteUserByEmail(email);

    }

    @Override
    public User authenticateUserLogin(String username, String password) throws UserException, ArgumentException {
        if (username == null || username.isEmpty()) {
            throw new ArgumentException("username/email cannot be null or empty");
        }

        if (password == null || password.isEmpty()) {
            throw new ArgumentException("Password cannot be null or empty");
        }

        User user = getUserByEmail(username);

        if (user == null) {
            throw new UserException("No user is associated with this email/username");
        }

        try {
            if (user.getPassword().equals(encryptPassword(password))) {
                return user;
            }
            throw new UserException("Invalid Login Details ");

        } catch (NoSuchAlgorithmException ex) {
            throw new UserException("Server error: Login Failed");
        }

    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public List<User> getAllUsersByRole(Role role) throws ArgumentException {

        if (role == null) {
            throw new ArgumentException("Invalid User");
        }

        if (checkIfUserRoleExist(role)) {
            throw new ArgumentException("Invalid User");
        }

        return dao.getAllUsersByRole(role);
    }

    private boolean checkIfUserRoleExist(Role role) throws ArgumentException {

        if (rServ.getRoleById(role.getRoleId()) != null) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        try {
            DBManager dbm = new DBManager("jdbc:mysql://localhost:3306/", "bakery?useSSL=false", "root", "root", "com.mysql.cj.jdbc.Driver");
            User user = new User("Michael","Kekana","0716336521","michael@gmail.com","michael",new Role("admin", 1));
            
            user.setAddress(new Address("15th Road", "Midrand", "Gauteng", "South Africa", "Joburg", "1632"));
            
                    String smtpHost = "smtp.gmail.com";
        int smtpPort = 587;
        
        String senderEmail = "topie4techg@gmail.com";
        String senderPassword = "axcicsdetlbsmxas";

        
        Properties  properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);

        
        EmailServiceImpl servic = new EmailServiceImpl(properties, senderEmail, senderPassword);
  
            
            System.out.println(new UserServiceImpl(new UserDaoImpl(dbm.getConnection()), new RoleServiceImpl(new RoleDaoImpl(dbm.getConnection())),
            AddressDaoImpl.getInstance(dbm.getConnection()),servic).
                    registerUser(user));
        } catch (ArgumentException |PasswordException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean AddNewAddress(Address address, String email, char type) {
        boolean bool = false;

        if (address == null || email == null || email.isEmpty() || type < -1) {
            return bool;
        }
        int addrId;
        if ((addrId = addrDao.addAddress(address)) < 1) {
            return bool;
        }
        
        bool = dao.addUserAddress(email, addrId, type);

        return bool;
    }

    @Override
    public HashMap<Integer, Address> getUserAddressesByEmail(String email) {
        
        if(email==null || email.isEmpty()){
            return null;
        }
        return addrDao.getUserAddressesByEmail(email);
    }
    
    
    @Override
    public void verifyUser(String email) throws Exception {

        EmailMessage msg = new EmailMessage(generateMesage(email), "text/html", "Email Verification", "ToPieFor", email);
        emailer.sendMail(msg);

    }

    private String generateMesage(String email) {
        StringBuilder builder = new StringBuilder();

        builder.append("<HTML><HEAD></HEAD><BODY>")
                .append("<p>dear customer,</p>")
                .append("<p>We would like you to Confirm <strong>ToPieFor</strong> registration ")
                .append("by clicking the button below:</p>")
                .append("<a href=\"")
                .append("http://localhost:8080/BakeryWebApp/sign-up.jsp?email=")
                .append(email)
                .append("\">Click here</a>")
                .append("<p>Kind regards,</p>")
                .append("<p>ToPieFor@techgiant's</p>")
                .append("</BODY></HTML>");

        return builder.toString();
    }

}
