/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.service;

import com.techgiants.topiefor.exception.OutOfStockException;
import com.techgiants.topiefor.exception.UserException;
import com.techgiants.topiefor.model.Order;
import com.techgiants.topiefor.status.Status;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author VM JELE
 */
public interface OrderService {
    
   boolean orderProducts(Order order, int addrId,String email) throws OutOfStockException;
   boolean confirmDeliveryOfOrder(int orderId,String email)throws UserException;
   List<Order> getAllOrders();
   List<Order> getAllUsersOrders(String email);
   boolean updateStatus(int orderId, Status status) throws ArithmeticException;
   void prepareDeliveryNote(HttpServletResponse response, int orderId) throws IOException;
   void prepareOrderInvoice(HttpServletResponse response,Order order)throws IOException;
   Order getOrderByUserIdAndInvoiceId(String email,int invoiceId);
    
}
