
package com.techgiants.topiefor.model;

import java.util.Objects;

public class QuantityUnit {
    private double qty;
    private Unit unit;

    public QuantityUnit() {
    }
    
    

    public QuantityUnit(double qty, Unit unit) {
        this.qty = qty;
        this.unit = unit;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "QuantityUnit{" + "qty=" + qty + ", unit=" + unit + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.qty) ^ (Double.doubleToLongBits(this.qty) >>> 32));
        hash = 23 * hash + Objects.hashCode(this.unit);
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
        final QuantityUnit other = (QuantityUnit) obj;
        return true;
    }
    
    
}
