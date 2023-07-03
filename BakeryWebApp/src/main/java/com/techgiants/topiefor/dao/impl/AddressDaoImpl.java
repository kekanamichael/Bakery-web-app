package com.techgiants.topiefor.dao.impl;

import com.techgiants.topiefor.dao.AddressDao;
import com.techgiants.topiefor.model.Address;
import com.techgiants.topiefor.model.Role;
import com.techgiants.topiefor.model.User;
import com.techgiants.topiefor.db.DBManager;
import com.techgiants.topiefor.exception.ArgumentException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressDaoImpl implements AddressDao {

    private static AddressDaoImpl addressDaoImpl;

    private Connection con = null;
    private PreparedStatement ps;

    private AddressDaoImpl(Connection con) {
        this.con = con;
    }
    
    

    public static AddressDaoImpl getInstance(Connection con) {
        if (addressDaoImpl == null) {
            addressDaoImpl = new AddressDaoImpl(con);
        }

        return addressDaoImpl;
    }

    @Override
    public Address getAddressByEmail(String email) {
        return new Address();
    }

    @Override
    public int addAddress(Address address) {
        int retVal = -1;
        if (con == null) {
            return retVal;
        }
        try {
            if(con.getAutoCommit()){
                con.setAutoCommit(false);
            }
            
            ps = con.prepareStatement("INSERT INTO ADDRESS (streetname, surburb, city, province, zipcode, country) VALUES(?,?,?,?,?,?)");
            ps.setString(1, address.getStreetName());
            ps.setString(2, address.getSuburb());
            ps.setString(3, address.getCity());
            ps.setString(4, address.getProvince());
            ps.setString(5, address.getPostalCode());
            ps.setString(6, address.getCountry());
            
            if (ps.executeUpdate() > 0) {
                retVal = getAddrId();
            } else {
                if (!con.getAutoCommit()) {
                    con.rollback();
                    con.setAutoCommit(true);
                }
            }

        } catch (SQLException e) {
            try {
                if (!con.getAutoCommit()) {
                    con.rollback();
                    con.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Failed to Add Address");
            }
        }
        return retVal;
    }
    
    
    

    private int getAddrId() throws SQLException {
        System.out.println("GEtting the Addr Id");
        ps = con.prepareStatement("SELECT LAST_INSERT_ID()");
        ResultSet rs = ps.executeQuery();
        int lastInsertId = -1;
        if (rs.next()) {
            lastInsertId = rs.getInt(1);
            //System.out.println("Got the Id from Dao");
            System.out.println("Last insertVal:" + lastInsertId);
        }
        return lastInsertId;
    }

    @Override
    public boolean deleteAddress(int addressId) {
        return false;
    }

    @Override
    public int checkIfAddressExists(Address addr) {
        int addrId = -1;

        if (con == null) {
            return addrId;
        }

        try {
            ps = con.prepareStatement("SELECT addressId FROM address where "
                    + "streetname = ? AND surburb= ? AND city= ? AND province= ? AND zipcode=? "
                    + "AND country= ?");
            ps.setString(1, addr.getStreetName());
            ps.setString(2, addr.getSuburb());
            ps.setString(3, addr.getCity());
            ps.setString(4, addr.getProvince());
            ps.setString(5, addr.getPostalCode());
            ps.setString(6, addr.getCountry());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                addrId = rs.getInt("addressId");
            } else {
                addrId = 0;
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

        return addrId;

    }

    public static void main(String[] args) {
        DBManager dbm = new DBManager("jdbc:mysql://localhost:3306/", "bakery?useSSL=false", "mie", "mie", "com.mysql.cj.jdbc.Driver");
        dbm.getConnection();

        User user = new User("Bhekii", "mokoenaa", "07164556556", "abcd@gmail.com", "123kfgytrree", new Role("customer", 2));
//            public Address(String streetName, String surbub, String city, String province, String country, int postalCode)
        //new AddressDaoImpl(dbm.getConnection()).addAddress(new Address("16th rode", "midrand", "jhb", "Gauteng", "South Africa", "1632"));
        System.out.println(new AddressDaoImpl(dbm.getConnection()).getUserAddressesByEmail("FSripp@gmail.com"));
    }

    @Override
    public HashMap<Integer,Address> getUserAddressesByEmail(String email) {
        HashMap<Integer,Address> userAddresses = null;
        
        if(con==null)return userAddresses;
        
        try {
//            public Address(String streetName, String surbub, String city, String province, String country, int postalCode)
            ps = con.prepareStatement("SELECT "
                    + "ad.streetName,ad.surburb,ad.city,ad.province,ad.country,ad.zipcode,"
                    + "ad.addressId,us.id "
                    + "FROM user_address us,address ad "
                    + "WHERE ad.addressId=us.addressId "
                    + "AND us.email=?");
            ps.setString(1, email);
            
            ResultSet res = ps.executeQuery();
            userAddresses = new HashMap<Integer,Address>();
            Address addr;
            int userAddrId;
            while(res.next()){
                addr = new Address();
                userAddrId = res.getInt("us.id");
                try {
                    addr.setAddressId(res.getInt("ad.addressId"));
                    addr.setStreetName(res.getString("ad.streetName"));
                    addr.setCity(res.getString("ad.city"));
                    addr.setCountry(res.getString("ad.country"));
                    addr.setPostalCode(res.getString("ad.zipcode"));
                    addr.setProvince(res.getString("ad.province"));
                    addr.setSuburb(res.getString("ad.surburb"));
                    
                } catch (ArgumentException ex) {
                    break;
                }
                
                userAddresses.put(userAddrId,addr);
                
            }
            
            
            
        } catch (SQLException ex) {
            System.out.println("SQL ERROR: "+ex.getMessage());
        }
        
       return userAddresses; 
    }

}
