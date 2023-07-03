package com.techgiants.topiefor.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Delivery {

    private int deliveryId;
    private LocalDateTime deliveryDate;
    private boolean delivered;

    public Delivery(LocalDateTime deliveryDate, boolean delivered) {
        this.deliveryDate = deliveryDate;
        this.delivered = delivered;
    }

    public Delivery(int deliveryId, LocalDateTime deliveryDate, boolean delivered) {
        this.deliveryId = deliveryId;
        this.deliveryDate = deliveryDate;
        this.delivered = delivered;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    @Override
    public String toString() {
        return "Delivery{" + "deliveryId=" + deliveryId + ", deliveryDate=" + deliveryDate + ", delivered=" + delivered + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.deliveryId;
        hash = 97 * hash + Objects.hashCode(this.deliveryDate);
        hash = 97 * hash + (this.delivered ? 1 : 0);
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
        final Delivery other = (Delivery) obj;
        return true;
    }

}
