package com.techgiants.topiefor.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private Connection con = null;
    private String dburl;
    private String database;
    private String dbuser;
    private String dbpass;
    private String driver;

    public DBManager(String dburl, String database, String dbuser, String dbpass, String driver) {
        this.dburl = dburl;
        this.database = database;
        this.dbuser = dbuser;
        this.dbpass = dbpass;
        this.driver = driver;
    }

    public Connection getConnection() {
        if (con == null) {
            createConnection();
        }
        return con;
    }

    private boolean createConnection() {
        boolean retVal = false;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
            return retVal;
        }
        System.out.println("Driver Loaded");
        String ourUri = dburl + database;
        try {
            con = DriverManager.getConnection(dburl + database, dbuser, dbpass);
            retVal = true;
        } catch (SQLException ex) {
            System.out.println("Could not connect: " + ex.getMessage());
            ex.printStackTrace();
            return retVal;
        }
        return retVal;
    }

    public boolean closeConnection() {
        boolean retVal = false;
        if (con != null) {
            try {
                con.close();
                retVal = true;
            } catch (SQLException ex) {
                System.out.println("Error closing connection: " + ex.getMessage());
            } finally {
                con = null;
            }
        }
        return retVal;
    }
}
