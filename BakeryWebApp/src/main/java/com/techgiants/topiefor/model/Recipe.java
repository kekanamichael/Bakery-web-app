/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.model;

import java.util.HashMap;

/**
 *
 * @author STUDIO 18
 */
public class Recipe {
    
    private int recipeId;
    private String name;
    private String description;
    private HashMap<Ingredient, QuantityUnit> ingredients;

    public Recipe() {
    }

    public Recipe(String name, String description, HashMap<Ingredient, QuantityUnit> ingredients) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }
    
    

    public Recipe(int recipeId, String name, String description, HashMap<Ingredient, QuantityUnit> ingredients) {
        this.recipeId = recipeId;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }

    public Recipe(int recipeId, String name) {
        this.recipeId = recipeId;
        this.name = name;
    }

    public Recipe(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<Ingredient, QuantityUnit> getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashMap<Ingredient, QuantityUnit>ingredients) {
        this.ingredients = ingredients;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String toString() {
        return "Recipe{" + "recipeId=" + recipeId + ",\n name=" + name + ",\n description=" + description + ",\n ingredients=" + ingredients.toString() + '}';
    }
    
    
    
}
