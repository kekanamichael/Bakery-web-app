
package com.techgiants.topiefor.dao;

import com.techgiants.topiefor.model.Delivery;
import java.util.List;


public interface DeliveryDao {
   
    Delivery getDeliveryById(int deliveryId);
    List<Delivery> getAllDeliveries();
    List<Delivery> getPendingDeliveries();
    List<Delivery> getAllDelivered();
}
