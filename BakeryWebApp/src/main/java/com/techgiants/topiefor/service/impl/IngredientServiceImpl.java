/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.service.impl;

import com.techgiants.topiefor.dao.IngredientDao;
import com.techgiants.topiefor.dao.impl.IngredientDaoImpl;
import com.techgiants.topiefor.db.DBManager;
import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Ingredient;
import com.techgiants.topiefor.service.IngredientService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author STUDIO 18
 */
public class IngredientServiceImpl implements IngredientService{
    
    private static IngredientServiceImpl impl;
    private IngredientDao ingreDao;
    
    private IngredientServiceImpl(IngredientDao ingreDao){
        this.ingreDao = ingreDao;
        
    }
    
    public static IngredientServiceImpl getInstance(IngredientDao ingreDao){
        if(impl==null)
            impl = new IngredientServiceImpl(ingreDao);
        
        return impl;
    }

    @Override
    public boolean addIngredient(Ingredient ingredient) throws ArgumentException {
        if(ingredient==null)
            throw new ArgumentException("Cannot add Null ingredient");
        
        return ingreDao.addIngredient(ingredient);
    }

    @Override
    public boolean updateIngredient(Ingredient ingredient) throws ArgumentException {
        if(ingredient==null)
            throw new ArgumentException("Cannot update a null ingredient");
        
        return ingreDao.updateIngredient(ingredient);
    }

    @Override
    public boolean deleteIngredient(int ingredientId, boolean active) throws ArgumentException {
       if(ingredientId<0)
           throw new ArgumentException("Cannot delete Ingredient with an Invalid Id");
       
       return ingreDao.deleteIngredient(ingredientId, active);
    }

    @Override
    public Ingredient getIngredientById(int ingredientId) throws ArgumentException {
        if(ingredientId<0)
            throw new ArgumentException("Cannot get Ingredient with an Invalid Id");
        
        return ingreDao.getIngredientById(ingredientId);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingreDao.getAllIngredients();
    }
    
    public static void main(String []args){
        DBManager dbm = new DBManager("jdbc:mysql://localhost:3306/", "bakery?useSSL=false", "mie", "mie", "com.mysql.cj.jdbc.Driver");
        //new IngredientDaoImpl(dbm.getConnection()).addIngredient(new Ingredient(1,"egg",6,5,true));
//        try {
        new IngredientServiceImpl(IngredientDaoImpl
                .getInstance(dbm.getConnection()))
                .getAllIngredients()
                .stream().forEach(System.out::println);
//        } catch (ArgumentException ex) {
//            Logger.getLogger(IngredientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
    
}
