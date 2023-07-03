package com.techgiants.topiefor.model;

import com.techgiants.topiefor.status.Status;
import com.techgiants.topiefor.unit.ConverterI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Order {

    private int orderId;
    private LocalDateTime orderDate;
    private Status status;
    private double totalAmount;
    private List<OrderItem> orderItems;
    private Delivery delivery;
    private Invoice invoice;
    private String userId;
    private String userName;

    public Order(int orderId, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    public Order() {
    }

    public Order(int orderId, LocalDateTime orderDate, Status status, List<OrderItem> orderItems, Delivery delivery, Invoice invoice) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = status;
        this.orderItems = orderItems;
        this.delivery = delivery;
        this.invoice = invoice;
    }

    public Order(int orderId, LocalDateTime orderDate, Status status, double totalAmount, Delivery delivery, Invoice invoice) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.delivery = delivery;
        this.invoice = invoice;
    }

    public Order(int id, Status orderStatus) {
        this.orderId = id;
        this.status = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getTotalAmount() {
        double totalAmount = 0;
        for (int i = 0; i < getOrderItems().size(); i++) {
            totalAmount += getOrderItems().get(i).getUnitPrice() * getOrderItems().get(i).getQuantity();
        }
        return totalAmount;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", orderDate=" + orderDate + ", status=" + status + ", totalAmount=" + totalAmount + ", orderItems=" + orderItems + ", delivery=" + delivery + ", invoice=" + invoice + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.orderId;
        hash = 17 * hash + Objects.hashCode(this.orderDate);
        hash = 17 * hash + Objects.hashCode(this.status);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.totalAmount) ^ (Double.doubleToLongBits(this.totalAmount) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.orderItems);
        hash = 17 * hash + Objects.hashCode(this.delivery);
        hash = 17 * hash + Objects.hashCode(this.invoice);
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
        final Order other = (Order) obj;
        return true;
    }

    public HashMap<Integer, QuantityUnit> getOrderIngredientQty() {
        HashMap<Integer, QuantityUnit> orderIngredients = new HashMap<>();
        HashMap<Ingredient, QuantityUnit> recIngreds;
        for (OrderItem i : orderItems) {

            recIngreds = i.getProduct().getRecipe().getIngredients();
            addIngredients(orderIngredients, recIngreds, i.getQuantity());

        }

        return orderIngredients;
    }

    private void addIngredients(HashMap<Integer, QuantityUnit> orderIngredients, HashMap<Ingredient, QuantityUnit> recIngreds, int qty) {
        Iterator iter = recIngreds.keySet().iterator();
        Ingredient ingred;
        QuantityUnit qtyU;
        double val;
        while (iter.hasNext()) {
            ingred = (Ingredient) iter.next();
            qtyU = recIngreds.get(ingred);
            if (!orderIngredients.containsKey(ingred.getIngredientId())) {
                val = ConverterI.convertUnits(qtyU.getUnit(), ingred.getUnit(), qtyU.getQty()) * qty;
                qtyU = new QuantityUnit(val, ingred.getUnit());
            } else {
                val = orderIngredients.get(ingred.getIngredientId()).getQty();
                val += ConverterI.convertUnits(qtyU.getUnit(), ingred.getUnit(), qtyU.getQty()) * qty;
                qtyU = new QuantityUnit(val, ingred.getUnit());

            }
            orderIngredients.put(ingred.getIngredientId(), qtyU);
        }

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
