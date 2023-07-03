/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.techgiants.topiefor.service;

import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Ingredient;
import java.util.List;

/**
 *
 * @author STUDIO 18
 */
public interface IngredientService {
    boolean addIngredient(Ingredient ingredient)throws ArgumentException;

    boolean updateIngredient(Ingredient ingredient)throws ArgumentException;

    boolean deleteIngredient(int ingredientId, boolean active)throws ArgumentException;

    Ingredient getIngredientById(int ingredientId)throws ArgumentException;

    List<Ingredient> getAllIngredients();
}
