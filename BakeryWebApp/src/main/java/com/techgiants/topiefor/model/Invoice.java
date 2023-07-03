package com.techgiants.topiefor.model;

import java.util.Objects;

public class Invoice {

    private int invoiceId;
    private boolean paid;
    private Payment payment;

    public Invoice(int invoiceId, boolean paid, Payment payment) {
        this.invoiceId = invoiceId;
        this.paid = paid;
        this.payment = payment;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Invoice{" + "invoiceId=" + invoiceId + ", paid=" + paid + ", payment=" + payment + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.invoiceId;
        hash = 83 * hash + (this.paid ? 1 : 0);
        hash = 83 * hash + Objects.hashCode(this.payment);
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
        final Invoice other = (Invoice) obj;
        return true;
    }
    
    



}
