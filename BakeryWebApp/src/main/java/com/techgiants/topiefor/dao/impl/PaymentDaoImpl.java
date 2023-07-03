package com.techgiants.topiefor.dao.impl;

import com.techgiants.topiefor.dao.PaymentDao;
import com.techgiants.topiefor.model.Payment;
import com.techgiants.topiefor.paymentmethod.PaymentMethod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {

    private static PaymentDaoImpl paymentDaoImpl;
    private Connection con = null;
    private PreparedStatement ps;

    private PaymentDaoImpl(Connection con) {
        this.con = con;
    }

    public static PaymentDaoImpl getInstance(Connection con) {
        if (paymentDaoImpl == null) {
            paymentDaoImpl = new PaymentDaoImpl(con);
        }
        return paymentDaoImpl;
    }

    @Override
    public List<Payment> getAllPayments() {
        List<Payment> paymentList = new ArrayList();
        if (con == null) {
            return paymentList;
        }
        try {
            ps = con.prepareStatement("SELECT paymentId, amount, datePaid, paymentMethod, invoiceId");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                paymentList.add(new Payment(rs.getInt("paymentId"), PaymentMethod.valueOf(rs.getString("paymentMethod")), rs.getDouble("amount"), rs.getTimestamp("datePaid").toLocalDateTime()));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all payments: " + e.getMessage());
        }
        return paymentList;
    }

    @Override
    public Payment getPaymentById(int paymentId) {
       Payment payment = null;
       if(con == null){
           return payment;
       }
        try {
            ps=con.prepareStatement("select paymentId, amount, datePaid, paymentMethod where paymentId=?");
            ps.setInt(1, paymentId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                payment=new Payment(rs.getInt("paymentId"), PaymentMethod.valueOf(rs.getString("paymentMethod")), rs.getDouble("amount"), rs.getTimestamp("datePaid").toLocalDateTime());
            }
        } catch (SQLException e) {
            System.out.println("ERROR GETTING PAYMENT BY ID: "+ e.getMessage());
        }
       return payment;
    }

    @Override
    public Payment getPaymentByInvoiceId(int invoiceId) {
             Payment payment = null;
       if(con == null){
           return payment;
       }
        try {
            ps=con.prepareStatement("select paymentId, amount, datePaid, paymentMethod where invoiceId=?");
            ps.setInt(1, invoiceId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                payment=new Payment(rs.getInt("paymentId"), PaymentMethod.valueOf(rs.getString("paymentMethod")), rs.getDouble("amount"), rs.getTimestamp("datePaid").toLocalDateTime());
            }
        } catch (SQLException e) {
            System.out.println("ERROR GETTING PAYMENT BY invoiceID: "+ e.getMessage());
        }
       return payment;
    }

}
