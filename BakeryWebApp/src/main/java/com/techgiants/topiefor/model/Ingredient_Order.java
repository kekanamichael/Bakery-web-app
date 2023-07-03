package com.techgiants.topiefor.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Ingredient_Order {

    private int id;
    private LocalDateTime deliveryDate;
    private LocalDateTime orderDate;
    private boolean delivered;
    private List<Ingredient_OrderItem> orderItems;

    public Ingredient_Order(int id, LocalDateTime deliveryDate, LocalDateTime orderDate, boolean delivered, List<Ingredient_OrderItem> orderItems) {
        this.id = id;
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
        this.delivered = delivered;
        this.orderItems = orderItems;
    }

    public Ingredient_Order(LocalDateTime orderDate, boolean delivered, List<Ingredient_OrderItem> orderItems) {
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
        this.delivered = delivered;
        this.orderItems = orderItems;
    }
    
    
    
     public Ingredient_Order(int id, LocalDateTime deliveryDate, LocalDateTime orderDate, boolean delivered) {
        this.id = id;
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
        this.delivered = delivered;
       
    }

    public Ingredient_Order() {
        
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public List<Ingredient_OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Ingredient_OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Ingredient_Order{" + "id=" + id + ", deliveryDate=" + deliveryDate + ", orderDate=" + orderDate + ", delivered=" + delivered + ", orderItems=" + orderItems + '}' + "\n";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.deliveryDate);
        hash = 41 * hash + Objects.hashCode(this.orderDate);
        hash = 41 * hash + (this.delivered ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ingredient_Order other = (Ingredient_Order) obj;
        return true;
    }

}
