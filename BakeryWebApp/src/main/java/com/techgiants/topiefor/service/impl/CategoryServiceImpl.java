package com.techgiants.topiefor.service.impl;

import com.techgiants.topiefor.dao.CategoryDao;
import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Category;
import com.techgiants.topiefor.service.CategoryService;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao catDao;
    private static CategoryServiceImpl impl;

    private CategoryServiceImpl(CategoryDao catDao) {
        this.catDao = catDao;
    }

    @Override
    public boolean addCategory(Category cat) throws ArgumentException {
        if (cat == null) {
            throw new ArgumentException("Cannot not Add null category");
        }

        return catDao.addCategory(cat);
    }

    @Override
    public boolean updateCategory(Category cat) throws ArgumentException {
        if (cat == null) {
            throw new ArgumentException("Cannot update a null category");
        }

        return catDao.updateCategory(cat);
    }

    @Override
    public boolean deleteCategory(int categoryId) throws ArgumentException {
        if (categoryId < 0) {
            throw new ArgumentException("Cannot delete Category with an invalid Id");
        }

        return catDao.deleteCategory(categoryId);
    }

    @Override
    public boolean activateCategory(int categoryId) throws ArgumentException {
        if (categoryId < 0) {
            throw new ArgumentException("Cannot delete Category with an invalid Id");
        }

        return catDao.activateCategory(categoryId);
    }

    @Override
    public Category getCategoryById(int categoryId) throws ArgumentException {
        if (categoryId < 0) {
            throw new ArgumentException("Cannot get Category with an invalid");
        }

        return catDao.getCategoryById(categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        return catDao.getAllCategories();
    }

    public static CategoryServiceImpl getInstance(CategoryDao catDao) {
        if (impl == null) {
            impl = new CategoryServiceImpl(catDao);
        }

        return impl;
    }

    public static void main(String[] args) {

    }

}
