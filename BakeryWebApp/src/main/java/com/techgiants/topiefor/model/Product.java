/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author STUDIO 18
 */
public class Product {
    
    private int productId;
    private String name;
    private String nutrientInfo;
    private String warnings;
    private double unitPrice;
    private Category category;
    private Recipe recipe;
    private boolean isActive;
    private String image;

    public Product(int productId, String name, String nutrientInfo, String warnings, double unitPrice, Category category, Recipe recipe, boolean isActive, String image) {
        this.productId = productId;
        this.name = name;
        this.nutrientInfo = nutrientInfo;
        this.warnings = warnings;
        this.unitPrice = unitPrice;
        this.category = category;
        this.recipe = recipe;
        this.isActive = isActive;
        this.image = image;
    }
    public Product( String name, String nutrientInfo, String warnings, double unitPrice, Category category, Recipe recipe, boolean isActive, String image) {
        this.name = name;
        this.nutrientInfo = nutrientInfo;
        this.warnings = warnings;
        this.unitPrice = unitPrice;
        this.category = category;
        this.recipe = recipe;
        this.isActive = isActive;
        this.image = image;
    }

    public Product() {
    }

    public Product(int productId, String name) {
        this.productId = productId;
        this.name = name;
    }
    
    
    

    public Product(String name, String nutrientInfo, String warnings, double unitPrice, Category category, Recipe recipe, boolean isActive) {
        this.name = name;
        this.nutrientInfo = nutrientInfo;
        this.warnings = warnings;
        this.unitPrice = unitPrice;
        this.category = category;
        this.recipe = recipe;
        this.isActive = isActive;
    }

    public Product(int productId, String name, String nutrientInfo, String warnings, double unitPrice, Category category, Recipe recipe, boolean isActive) {
        this.productId = productId;
        this.name = name;
        this.nutrientInfo = nutrientInfo;
        this.warnings = warnings;
        this.unitPrice = unitPrice;
        this.category = category;
        this.recipe = recipe;
        this.isActive = isActive;
    }
    public Product(int productId, String name, String nutrientInfo, String warnings, double unitPrice,  boolean isActive) {
        this.productId = productId;
        this.name = name;
        this.nutrientInfo = nutrientInfo;
        this.warnings = warnings;
        this.unitPrice = unitPrice;
        this.isActive = isActive;
    }
    

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNutrientInfo() {
        return nutrientInfo;
    }

    public void setNutrientInfo(String nutrientInfo) {
        this.nutrientInfo = nutrientInfo;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.productId;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.nutrientInfo);
        hash = 97 * hash + Objects.hashCode(this.warnings);
        hash = 97 * hash + Objects.hashCode(this.category);
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
        final Product other = (Product) obj;
        if (this.productId != other.productId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.nutrientInfo, other.nutrientInfo)) {
            return false;
        }
        if (!Objects.equals(this.warnings, other.warnings)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", name=" + name + ", nutrientInfo=" + nutrientInfo + ", warnings=" + warnings + ", unitPrice=" + unitPrice + ", category=" + category + ", recipe=" + recipe + ", isActive=" + isActive + '}';
    }

    

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public HashMap<Integer, Double> qtysOfIngredient() {
        HashMap<Integer, Double> orderIngredients = new HashMap<>();
        
        Iterator iter = recipe.getIngredients().keySet().iterator();
        Ingredient ingred;
        QuantityUnit qtyU;
        double val;
        while(iter.hasNext()){
            ingred = (Ingredient) iter.next();
            qtyU = recipe.getIngredients().get(ingred);
            if(!orderIngredients.containsKey(ingred.getIngredientId())){
                val = qtyU.getQty();
            }else{
                val = orderIngredients.get(ingred.getIngredientId());
                val += qtyU.getQty();
                
            }
            orderIngredients.put(ingred.getIngredientId(), val);
        }
        
        return orderIngredients; 
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    
}
