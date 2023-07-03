package com.techgiants.topiefor.dao.impl;

import com.techgiants.topiefor.dao.UserDao;
import com.techgiants.topiefor.model.Role;
import com.techgiants.topiefor.model.User;
import com.techgiants.topiefor.db.DBManager;
import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private Connection con = null;
    private PreparedStatement ps;

    private static UserDaoImpl dao;

    public UserDaoImpl(Connection con) {
        this.con = con;
    }

    public static UserDaoImpl getInstance(Connection con) {
        if (dao == null) {
            dao = new UserDaoImpl(con);
        }

        return dao;
    }

    @Override
    public boolean addUserAddress(String email, int addrId,char type) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }

        try {
            ps = con.prepareStatement("INSERT INTO USER_ADDRESS(email,addressId,type) VALUES(?,?,?)");
            ps.setString(1, email);
            ps.setInt(2, addrId);
            ps.setString(3, Character.toString(type));

            if (ps.executeUpdate() > 0) {
                con.commit();
                retVal = true;
            }

        } catch (SQLException d) {
            System.out.println("Error User_Address: " + d.getMessage());
            try {
                if (!con.getAutoCommit()) {
                    con.rollback();

                }
            } catch (SQLException ex) {

            }

        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {

            }
        }

        return retVal;
    }

    
    @Override
    public boolean addUser(User user) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }

        try {
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO USER (email, name, surname, mobileNum, password, roleId, isActive) VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getName());
            ps.setString(3, user.getSurname());
            ps.setString(4, user.getMobileNum());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getRole().getRoleId());
            ps.setBoolean(7, true);

            if (ps.executeUpdate() > 0) {
                retVal = true;
            }

        } catch (SQLException e) {
            System.out.println("Could not add a user:" + e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    throw new RuntimeException("Failed to Add User");
                }
            }
        }
        return retVal;
    }

    @Override
    public boolean upadateUser(User user) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }
        try {
            ps = con.prepareStatement("UPDATE USER SET name = ?, surname = ?, mobileNum =? WHERE email = ?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getMobileNum());
            ps.setString(4, user.getEmail());
            if (ps.executeUpdate() > 0) {

                retVal = true;
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
        return retVal;
    }

    @Override
    public boolean deleteUserByEmail(String email) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }
        try {
            ps = con.prepareStatement("UPDATE USER SET isActive=? WHERE email=?");
            ps.setBoolean(1, false);
            ps.setString(2, email);
            if (ps.executeUpdate() > 0) {
                retVal = true;
            }
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
        return retVal;
    }

    @Override
    public User getUserById(int userId) {
        User user = null;
        if (con == null) {
            return user;
        }
        try {
            ps = con.prepareStatement("SELECT name, surname, mobileNum, email, roleId, isActive, password FROM user WHERE userId = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                user = new User(rs.getString("name"), rs.getString("surname"), rs.getString("mobileNum"), rs.getString("email"), rs.getString("password"), new Role("customer", 2));
            } else {

            }
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        Address addr = null;
        
        if (con == null) {
            return user;
        }
        try {
            ps = con.prepareStatement("SELECT us.name, us.surname, us.mobileNum, us.email, us.roleId, us.isActive, us.password, "
                    + "ad.streetname, ad.city, ad.country, ad.surburb, ad.zipcode, ad.addressId, ad.province, "
                    + "r.role, r.id "
                    + "FROM role r, user_address usa, address ad, user us "
                    + "WHERE us.email = usa.email AND " //join user and user_address
                    + "usa.addressId = ad.addressId AND " //join user_address and address 
                    + "r.id = us.roleId AND " //join user and role 
                    + "usa.type = ? AND " //restrict it by recidential addr
                    + "us.email = ? "); //rectirct it to by email
            ps.setString(1,"r");
            ps.setString(2, email);
           
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                addr = new Address(rs.getString("ad.streetname"),rs.getString("ad.city")
                        ,rs.getString("ad.province"), rs.getString("ad.country")
                        ,rs.getString("ad.surburb"),rs.getString("ad.zipcode"),rs.getInt("ad.addressId"));
                System.out.println(addr);
               user = new User(rs.getString("us.name"), rs.getString("us.surname"), 
                       rs.getString("us.mobileNum"), rs.getString("us.email"), rs.getString("us.password"),
                       new Role(rs.getString("r.role"),rs.getInt("r.id")));
                try {
                    user.setAddress(addr);
                } catch (ArgumentException ex) {
                    user = null;
                }
               
            }
            
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
        
        
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>();
    }

    @Override
    public List<User> getAllUsersByRole(Role role) {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        DBManager dbm = new DBManager("jdbc:mysql://localhost:3306/", "bakery?useSSL=false", "root", "root", "com.mysql.cj.jdbc.Driver");
        dbm.getConnection();
        Address addr = new Address("15th Road", "Durban","KwaZulu Natal","South Africa","KwaMashu", "2051");
      
//        public User(String name, String surname, String mobileNum, String email, int userId, String password)
        //new UserDaoImpl(dbm.getConnection()).addUser(new User("Bhekii", "mokoenaa", "07164556556", "gef@gmail.com", "123kfgytrree", new Role("customer", 2)));
        System.out.println(new UserDaoImpl(dbm.getConnection()).getUserByEmail("michael@gmail.com"));
    }

    @Override
    public boolean hasHomeAddress(String email) {
        boolean hasHomeA = false;
        
        if (con == null) {
            return hasHomeA;
        }

        try {
            ps = con.prepareStatement("SELECT addressId "
                    + "FROM address");

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
               hasHomeA = true;
            }

        } catch (SQLException ex) {
            try {
                if (!con.getAutoCommit()) {
                    con.rollback();
                    con.setAutoCommit(true);

                }
            } catch (SQLException ex1) {
                throw new RuntimeException("Failed to Check if Address Exits");
            }
        }

        return hasHomeA;
        
        
        
    }

   

}
