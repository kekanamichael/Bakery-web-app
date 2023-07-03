package com.techgiants.topiefor.model;

import java.util.Objects;

public class Ingredient_OrderItem {
    private int ingredient_OrderItemid;
    private double quantity;
    private Ingredient ingredient;
   // private Ingredient_Order ingredientOrder;

    public Ingredient_OrderItem(int ingredient_OrderItemid, double quantity, Ingredient ingredient) {
        this.ingredient_OrderItemid = ingredient_OrderItemid;
        this.quantity = quantity;
        this.ingredient = ingredient;
        
    }

    public Ingredient_OrderItem(double quantity, Ingredient ingredient) {
        this.quantity = quantity;
        this.ingredient = ingredient;
    }
    
    

    public Ingredient_OrderItem() {
        
    }

    public int getId() {
        return ingredient_OrderItemid;
    }

    public void setId(int ingredient_OrderItemid) {
        this.ingredient_OrderItemid = ingredient_OrderItemid;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
    
    @Override
    public String toString() {
        return "Ingredient_OrderItem{" + "id=" + ingredient_OrderItemid + ", quantity=" + quantity + ", ingredient=" + ingredient +  '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.ingredient_OrderItemid;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.quantity) ^ (Double.doubleToLongBits(this.quantity) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.ingredient);
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
        final Ingredient_OrderItem other = (Ingredient_OrderItem) obj;
        return true;
    }
    
}
