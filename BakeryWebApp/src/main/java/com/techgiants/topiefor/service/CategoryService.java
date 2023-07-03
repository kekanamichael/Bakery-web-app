/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.service;

import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Category;
import java.util.List;

/**
 *
 * @author STUDIO 18
 */
public interface CategoryService {
    
    boolean addCategory(Category cat) throws ArgumentException;

    boolean updateCategory(Category cat)throws ArgumentException;

    boolean deleteCategory(int categoryId)throws ArgumentException;
    
    boolean activateCategory(int categoryId)throws ArgumentException;

    Category getCategoryById(int categoryId)throws ArgumentException;

    List<Category> getAllCategories();
    
}
