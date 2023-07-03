
package com.techgiants.topiefor.model;

import com.techgiants.topiefor.paymentmethod.PaymentMethod;
import java.time.LocalDateTime;
import java.util.Objects;

public class Payment {
    private int paymentId;
    private PaymentMethod paymentMethod;
    private double amount;
    private LocalDateTime datePaid;

    public Payment(int paymentId, PaymentMethod paymentMethod, double amount, LocalDateTime datePaid) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.datePaid = datePaid;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(LocalDateTime datePaid) {
        this.datePaid = datePaid;
    }

    @Override
    public String toString() {
        return "Payment{" + "paymentId=" + paymentId + ", paymentMethod=" + paymentMethod + ", amount=" + amount + ", datePaid=" + datePaid + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.paymentId;
        hash = 89 * hash + Objects.hashCode(this.paymentMethod);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.amount) ^ (Double.doubleToLongBits(this.amount) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.datePaid);
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
        final Payment other = (Payment) obj;
        return true;
    }
    
    
}
