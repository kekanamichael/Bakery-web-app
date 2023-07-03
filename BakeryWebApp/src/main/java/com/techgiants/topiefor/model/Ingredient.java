
package com.techgiants.topiefor.model;

import java.util.Objects;

public class Ingredient {
   private int ingredientId;
    private String name;
    private double stock;
    private double minStockOnHand;
    private boolean isActive;
    private Unit unit;

    public Ingredient(){
        
    }
    public Ingredient(int ingredientId, String name, double stock, double minStockOnHand, boolean isActive, Unit unit) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.stock = stock;
        this.minStockOnHand = minStockOnHand;
        this.isActive = isActive;
        this.unit = unit;
    }

    public Ingredient(int ingredientId, String name, double minStockOnHand) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.minStockOnHand = minStockOnHand;
    }
    
    

    public Ingredient(int ingredientId, String name) {
        this.ingredientId = ingredientId;
        this.name = name;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getMinStockOnHand() {
        return minStockOnHand;
    }

    public void setMinStockOnHand(double minStockOnHand) {
        this.minStockOnHand = minStockOnHand;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "ingredientId=" + ingredientId + ", name=" + name + ", stock=" + stock + ", minStockOnHand=" + minStockOnHand + ", isActive=" + isActive + ", unit=" + unit + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.ingredientId;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.stock) ^ (Double.doubleToLongBits(this.stock) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.minStockOnHand) ^ (Double.doubleToLongBits(this.minStockOnHand) >>> 32));
        hash = 71 * hash + (this.isActive ? 1 : 0);
        hash = 71 * hash + Objects.hashCode(this.unit);
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
        final Ingredient other = (Ingredient) obj;
        return true;
    }

}
