/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.techgiants.topiefor.service;

import com.techgiants.topiefor.model.Ingredient_Order;
import java.util.List;

/**
 *
 * @author STUDIO 18
 */
public interface Ingredient_OrderService {
    
    boolean addIngredient_Order(Ingredient_Order order);
    List<Ingredient_Order> getAllIngredient_Orders();
    boolean confirmIngredientOrderDelivery(int ingredientOrderId);
}
