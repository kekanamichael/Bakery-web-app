package com.techgiants.topiefor.dao.impl;

import com.techgiants.topiefor.dao.DeliveryDao;
import com.techgiants.topiefor.db.DBManager;
import com.techgiants.topiefor.model.Delivery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDaoImpl implements DeliveryDao {

    private static DeliveryDaoImpl deliveryDaoImpl;
    private Connection con = null;
    private PreparedStatement ps;

    private DeliveryDaoImpl(Connection con) {
        this.con = con;
    }

    public static DeliveryDaoImpl getInstance(Connection con) {
        if (deliveryDaoImpl == null) {
            deliveryDaoImpl = new DeliveryDaoImpl(con);
        }
        return deliveryDaoImpl;
    }

    @Override
    public Delivery getDeliveryById(int deliveryId) {
        Delivery delivery = null;
        if (con == null) {
            return delivery;
        }
        try {
            ps = con.prepareStatement("SELECT deliveryDate, delivered, order_id FROM delivery WHERE deliveryId=?");
            ps.setInt(1, deliveryId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                delivery = new Delivery(rs.getTimestamp("deliveryDate") == null ? null : rs.getTimestamp("deliveryDate").toLocalDateTime(), rs.getBoolean("delivered"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return delivery;
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        List<Delivery> deliveries = new ArrayList();
        if (con == null) {
            return deliveries;
        }
        try {
            ps = con.prepareStatement("SELECT deliveryDate, delivered, order_id FROM delivery");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                deliveries.add(new Delivery(rs.getInt("deliveryId"),rs.getTimestamp("deliveryDate").toLocalDateTime(), rs.getBoolean("delivered")));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all deliveries: " + e.getMessage());
        }
        return deliveries;
    }
    
       @Override
    public List<Delivery> getPendingDeliveries() {
        List<Delivery> deliveries = new ArrayList();
        if (con == null) {
            return deliveries;
        }
        try {
            ps = con.prepareStatement("SELECT deliveryId, deliveryDate, delivered, order_id FROM delivery WHERE delivered=false");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                deliveries.add(new Delivery(rs.getInt("deliveryId"),rs.getTimestamp("deliveryDate") == null ? null : rs.getTimestamp("deliveryDate").toLocalDateTime(), rs.getBoolean("delivered")));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return deliveries;
    }

    @Override
    public List<Delivery> getAllDelivered() {
        List<Delivery> deliveries = new ArrayList();
        if (con == null) {
            return deliveries;
        }
        try {
            ps = con.prepareStatement("SELECT deliveryId, deliveryDate, delivered, order_id FROM delivery WHERE delivered=?");
            ps.setBoolean(1, true);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                deliveries.add(new Delivery(rs.getInt("deliveryId"),rs.getTimestamp("deliveryDate") == null ? null : rs.getTimestamp("deliveryDate").toLocalDateTime(), rs.getBoolean("delivered")));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return deliveries;
    }


    public static void main(String[] args) {
        DBManager dbm = new DBManager("jdbc:mysql://localhost:3306/", "bakery?useSSL=false", "root", "root", "com.mysql.cj.jdbc.Driver");
        dbm.getConnection();
        // DeliveryDaoImpl.getInstance(dbm.getConnection()).addDelivery(new Delivery(LocalDateTime.MIN, true, new Order(1, null)), 1);
        System.out.println(DeliveryDaoImpl.getInstance(dbm.getConnection()).getPendingDeliveries());

    }
}
