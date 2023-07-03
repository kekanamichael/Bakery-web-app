package com.techgiants.topiefor.dao;

import com.techgiants.topiefor.model.DeliveryNote;
import com.techgiants.topiefor.model.Order;
import com.techgiants.topiefor.model.OrderItem;
import com.techgiants.topiefor.status.Status;
import java.util.List;

public interface OrderDao {

    int addOrder(Order order,  int id);

    boolean updateOrderStatus(int orderId, Status status);

    boolean addOrderItem(OrderItem item, int orderId);

    List<Order> getAllOrders();

    List<OrderItem> getAllOrderItemsByOrderId(int orderId);
    
    List<Order> getAllUserOrdersByUserId(String email);
    
    DeliveryNote getDeliveryNoteByOrderId(int orderId);
    
    Order getOrderByUserIdAndInvoiceId(String email,int invoiceId);
    Order getOrderByUserIdAndOrderId(String email,int orderId);

}
