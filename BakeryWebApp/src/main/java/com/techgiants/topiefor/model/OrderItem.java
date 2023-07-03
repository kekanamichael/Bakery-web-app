package com.techgiants.topiefor.model;

import java.util.Objects;

public class OrderItem {

    private int orderItemId;
    private int quantity;
    private double unitPrice;
    private Product product;

    public OrderItem(int orderItemId, int quantity, double unitPrice, Product product) {
        this.orderItemId = orderItemId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.product = product;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrdeItem{" + "orderItemId=" + orderItemId + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", product=" + product + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.orderItemId;
        hash = 97 * hash + this.quantity;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.unitPrice) ^ (Double.doubleToLongBits(this.unitPrice) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.product);
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
        final OrderItem other = (OrderItem) obj;
        return true;
    }
    
    
}
