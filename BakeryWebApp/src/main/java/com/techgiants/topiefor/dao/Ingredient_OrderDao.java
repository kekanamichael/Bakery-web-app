package com.techgiants.topiefor.dao;

import com.techgiants.topiefor.model.Ingredient_Order;
import com.techgiants.topiefor.model.Ingredient_OrderItem;
import java.util.List;

public interface Ingredient_OrderDao {

    List<Ingredient_Order> getAllIngredientsOrder();

    boolean addIngredient_Order(Ingredient_Order order);

    boolean updateIngredient_Order(int ingredient_OrderId);
    
    boolean isOrderDelivered(int orderId);

    boolean addOrderItem(Ingredient_OrderItem item, int ingredientOrderId);

    List<Ingredient_OrderItem> getAllOrderItemsByOrderId(int id);
    
}
