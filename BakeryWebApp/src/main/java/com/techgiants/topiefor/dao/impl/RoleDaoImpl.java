package com.techgiants.topiefor.dao.impl;

import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Role;
import com.techgiants.topiefor.dao.RoleDao;
import com.techgiants.topiefor.db.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleDaoImpl implements RoleDao {

    private static RoleDaoImpl roleDaoImpl = null;
    private Connection con = null;
    private PreparedStatement ps;

    public RoleDaoImpl(Connection con){
        this.con = con;
    }
    
    public static RoleDaoImpl getInstance(Connection con){
        
        if(roleDaoImpl==null){
          roleDaoImpl = new RoleDaoImpl(con);
        }
        
        return roleDaoImpl;
    }
    
    @Override
    public Role getRole(int roleId) {
        Role rl = null;

        if (con == null) {
            return rl;
        }
        try {
            ps = con.prepareStatement("SELECT Id, role FROM ROLE WHERE Id = ?");
            ps.setInt(1, roleId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rl = new Role(rs.getString("role"));
                try {
                    rl.setRoleId(rs.getInt("Id"));
                } catch (ArgumentException ex) {
                    Logger.getLogger(RoleDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    rl.setRoleId(roleId);
                } catch (ArgumentException ex) {
                    
                }
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return rl;
    }

    public  void main(String[] args) {
        Connection con = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakery", "root", "root");
//        } catch (ClassNotFoundException cle) {
//        } catch (SQLException ex) {
//        }
//        if (con != null) {
//            System.out.println("Got connection");
//        }
//        DBManager dbm = new DBManager(url, database, driver, username, password);

        DBManager dbm = new DBManager("jdbc:mysql://localhost:3306/", "bakery?useSSL=false", "mie", "mie", "com.mysql.cj.jdbc.Driver");
        dbm.getConnection();
        System.out.println(new RoleDaoImpl(dbm.getConnection()).getRole(1));

    }

}
