package com.techgiants.topiefor.dao.impl;

import com.techgiants.topiefor.dao.CategoryDao;
import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Category;
import com.techgiants.topiefor.db.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDaoImpl implements CategoryDao {

    private Connection con = null;
    private PreparedStatement ps;
    private static CategoryDaoImpl impl;

    public static CategoryDaoImpl getInstance(Connection con) {
        if (impl == null) {
            impl = new CategoryDaoImpl(con);
        }

        return impl;
    }

    private CategoryDaoImpl(Connection con) {
        this.con = con;
    }

    @Override
    public boolean addCategory(Category cat) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }
        try {
            ps = con.prepareStatement("INSERT INTO CATEGORY (name, description, isActive) VALUES(?,?,?)");
            ps.setString(1, cat.getName());
            ps.setString(2, cat.getDescription());
            ps.setBoolean(3, true);
            if (ps.executeUpdate() > 0) {
                retVal = true;
            }
        } catch (SQLException e) {
            System.out.println("Cannot add category: " + e.getMessage());
        }

        return retVal;
    }

    @Override
    public boolean updateCategory(Category cat) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }
        try {
            ps = con.prepareStatement("UPDATE CATEGORY SET description = ?, name=?, isActive=? WHERE categoryId = ?");
            ps.setString(1, cat.getDescription());
            ps.setString(2, cat.getName());
            ps.setBoolean(3, true);
            ps.setInt(4, cat.getCategoryId());
            if (ps.executeUpdate() > 0) {
                retVal = true;
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
        return retVal;
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }
        try {
            ps = con.prepareStatement("UPDATE CATEGORY SET isActive=? WHERE categoryId=?");
            ps.setBoolean(1, false);
            ps.setInt(2, categoryId);

            if (ps.executeUpdate() > 0) {
                retVal = true;
            }

        } catch (SQLException ex) {
            System.out.println("SQL ERROR: " + ex.getMessage());
        }
        return retVal;
    }

    @Override
    public boolean activateCategory(int categoryId) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }
        try {
            ps = con.prepareStatement("UPDATE CATEGORY SET isActive=? WHERE categoryId=?");
            ps.setBoolean(1, true);
            ps.setInt(2, categoryId);

            if (ps.executeUpdate() > 0) {
                retVal = true;
            }

        } catch (SQLException ex) {
            System.out.println("SQL ERROR: " + ex.getMessage());
        }
        return retVal;
    }

    @Override
    public Category getCategoryById(int categoryId) {
        Category cat = null;

        if (con == null) {
            return cat;
        }
        try {
            ps = con.prepareStatement("SELECT categoryId, name, description, isActive FROM Category WHERE categoryId = ?");
            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cat = new Category(rs.getString("name"), rs.getInt("categoryId"), rs.getString("description"), rs.getBoolean("isActive"));
                cat.setCategoryId(categoryId);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("SQL ERROR: " + ex.getMessage());
        } catch (ArgumentException ex) {
            cat = Category();
        }
        return cat;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> catList = new ArrayList();
        if (con == null) {
            return catList;
        }
        try {
            ps = con.prepareStatement("SELECT categoryId, name,description,isActive FROM Category");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                try {
                    catList.add(new Category(rs.getString("name"), rs.getInt("categoryId"), rs.getString("description"), rs.getBoolean("isActive")));
                } catch (ArgumentException ex) {
                    catList.add(new Category());
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
        return catList;
    }

    public static void main(String[] args) throws ArgumentException {
        DBManager dbm = new DBManager("jdbc:mysql://localhost:3306/", "bakery?useSSL=false", "root", "root", "com.mysql.cj.jdbc.Driver");
//        try {
        // new CategoryDaoImpl(dbm.getConnection()).addCategory( new Category("Muffins", 5, "freshly baked", true));
        //System.out.println( new CategoryDaoImpl(dbm.getConnection()).getCategoryById(1));
        new CategoryDaoImpl(dbm.getConnection()).getAllCategories().stream().forEach(System.out::println);
        //        } catch (ArgumentException ex) {
        //            System.out.println("Error: "+ex.getMessage());
        //        }
        System.out.println(new CategoryDaoImpl(dbm.getConnection()).updateCategory(new Category("Bread", 1, "White", true)));

    }

    private Category Category() {
        return new Category();
    }

}
