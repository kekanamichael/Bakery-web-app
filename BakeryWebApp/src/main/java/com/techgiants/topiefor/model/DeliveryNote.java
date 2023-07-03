/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.model;

import java.time.LocalDateTime;

/**
 *
 * @author Admin
 */
public class DeliveryNote {
    
    private String userName;
    private String userSurname;
    private Address address;
    private int orderId;
    private int deliveryId;
    private LocalDateTime orderDate;

    public DeliveryNote() {
    }

    public DeliveryNote(String userName, String userSurname, Address address, int orderId, int deliveryId, LocalDateTime orderDate) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.address = address;
        this.orderId = orderId;
        this.deliveryId = deliveryId;
        this.orderDate = orderDate;
    }
    
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "DeliveryNote{" + "userName=" + userName + ", userSurname=" + userSurname + ", address=" + address+ ", orderId=" + orderId + ", deliveryId=" + deliveryId + ", orderDate=" + orderDate + '}';
    }
    
    
    
    
}
