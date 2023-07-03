package com.techgiants.topiefor.dao;

import com.techgiants.topiefor.dao.*;
import com.techgiants.topiefor.model.Category;
import java.util.List;

public interface CategoryDao {

    boolean addCategory(Category cat);

    boolean updateCategory(Category cat);

    boolean deleteCategory(int categoryId);
    
    boolean activateCategory(int categoryId);

    Category getCategoryById(int categoryId);

    List<Category> getAllCategories();

}
