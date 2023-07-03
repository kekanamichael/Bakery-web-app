package com.techgiants.topiefor.dao;

import com.techgiants.topiefor.model.Ingredient;
import java.util.List;

public interface IngredientDao {

    boolean addIngredient(Ingredient ingredient);

    boolean updateIngredient(Ingredient ingredient);

    boolean deleteIngredient(int ingredientId, boolean active);

    Ingredient getIngredientById(int ingredientId);

    List<Ingredient> getAllIngredients();
    double getStockByIngredientId(int ingredId);
    boolean updateStockByIngredientId(int ingredId,double stock);

}
