/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.dao.impl;

import com.techgiants.topiefor.dao.ProductDao;
import com.techgiants.topiefor.db.DBManager;
import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Category;
import com.techgiants.topiefor.model.Ingredient;
import com.techgiants.topiefor.model.Product;
import com.techgiants.topiefor.model.QuantityUnit;
import com.techgiants.topiefor.model.Recipe;
import com.techgiants.topiefor.model.Unit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author STUDIO 18
 */
public class ProductDaoImpl implements ProductDao {

    private static ProductDaoImpl impl;
    private Connection conn = null;
    private PreparedStatement ps;
    private int recipeId = -1;

    private ProductDaoImpl(Connection conn) {
        this.conn = conn;
    }

    public static ProductDaoImpl getInstance(Connection conn) {
        if (impl == null) {
            impl = new ProductDaoImpl(conn);
        }

        return impl;
    }

    @Override
    public Product getProductById(int productId) {
        Product prod = null;

        if (conn == null) {
            return prod;
        }

        try {
            ps = conn.prepareStatement("SELECT "
                    + "p.productId,p.name,p.image,p.nutrientInfo,p.warnings,p.unitPrice,p.category_id,p.recipeId,p.isActive, "
                    + "c.categoryId,c.description,c.isActive,c.name FROM product p,category c "
                    + "where productId=? "
                    + "AND c.categoryId = p.category_id ");
            ps.setInt(1, productId);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                try {
                    prod = new Product(res.getInt("p.productId"), res.getString("p.name"), res.getString("p.nutrientInfo"), res.getString("p.warnings"), res.getDouble("p.unitPrice"), new Category(res.getString("c.name"), res.getInt("c.categoryId"), res.getString("c.description"), res.getBoolean("c.isActive")), new Recipe(), res.getBoolean("isActive"));
                    prod.setImage(res.getString("p.image"));
                    prod.setRecipe(getRecipeByProductId(productId));
                } catch (ArgumentException ex) {
                    Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return prod;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = null;

        try {
            ps = conn.prepareStatement("SELECT pro.productId,pro.name,pro.nutrientInfo, pro.image,"
                    + "pro.warnings,pro.unitPrice,cat.categoryId,cat.name,r.name,pro.isActive,r.recipeId,cat.isActive,cat.description "
                    + "FROM product pro, category cat,recipe r "
                    + "WHERE pro.recipeId=r.recipeId "
                    + "AND pro.category_id = cat.categoryId "
                    );

            ResultSet res = ps.executeQuery();

//            Product pro = null;
            products = new ArrayList<>();
            Product p;
            Recipe r;
            Category c;
            while (res.next()) {
                try {

                    c = new Category(res.getString("cat.name"), res.getInt("cat.categoryId"), res.getString("cat.description"), res.getBoolean("cat.isActive"));
                    p = new Product(res.getInt("pro.productId"), res.getString("pro.name"), res.getString("pro.nutrientInfo"), res.getString("pro.warnings"), res.getDouble("pro.unitPrice"), res.getBoolean("pro.isActive"));
                    p.setImage(res.getString("pro.image"));
                    r = getRecipeByProductId(p.getProductId());
                    p.setRecipe(r);
                    p.setCategory(c);
                    products.add(p);

                } catch (ArgumentException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }

            }

        } catch (SQLException ex) {
            System.out.println("SQL ERROR:" + ex.getMessage());
        }

        return products;
    }

    @Override
    public List<Product> getAllActiveProducts() {
        List<Product> products = null;

        try {
            ps = conn.prepareStatement("SELECT pro.productId,pro.name,pro.nutrientInfo, pro.image,"
                    + "pro.warnings,pro.unitPrice,cat.categoryId,cat.name,r.name,pro.isActive,r.recipeId,cat.isActive,cat.description "
                    + "FROM product pro, category cat,recipe r "
                    + "WHERE pro.recipeId=r.recipeId "
                    + "AND pro.category_id = cat.categoryId "
                    + "AND pro.isActive=true ");

            ResultSet res = ps.executeQuery();

//            Product pro = null;
            products = new ArrayList<>();
            Product p;
            Recipe r;
            Category c;
            while (res.next()) {
                try {

                    c = new Category(res.getString("cat.name"), res.getInt("cat.categoryId"), res.getString("cat.description"), res.getBoolean("cat.isActive"));
                    p = new Product(res.getInt("pro.productId"), res.getString("pro.name"), res.getString("pro.nutrientInfo"), res.getString("pro.warnings"), res.getDouble("pro.unitPrice"), res.getBoolean("pro.isActive"));
                    p.setImage(res.getString("pro.image"));
                    r = getRecipeByProductId(p.getProductId());
                    p.setRecipe(r);
                    p.setCategory(c);
                    products.add(p);

                } catch (ArgumentException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }

            }

        } catch (SQLException ex) {
            System.out.println("SQL ERROR:" + ex.getMessage());
        }

        return products;
    }

    @Override
    public List<Product> getAllProductByCategoryId(int categoryId) {
        List<Product> products = null;

        try {
            ps = conn.prepareStatement("SELECT pro.productId,pro.name,pro.nutrientInfo,"
                    + "pro.warnings,pro.unitPrice,cat.categoryId,cat.name,r.name,pro.isActive,r.recipeId,cat.isActive,cat.description "
                    + "FROM product pro, category cat,recipe r "
                    + "WHERE pro.recipeId=r.recipeId "
                    + "AND pro.category_id = cat.categoryId "
                    + "AND pro.isActive=true "
                    + "AND pro.category_id=?");
            ps.setInt(1, categoryId);

            ResultSet res = ps.executeQuery();

//            Product pro = null;
            products = new ArrayList<>();
            Product p;
            Recipe r;
            Category c;
            while (res.next()) {
                try {
                    //                    public Product(int productId, String name, String nutrientInfo, String warnings, double unitPrice, Category category, Recipe recipe, boolean isActive) {

                    c = new Category(res.getString("cat.name"), res.getInt("cat.categoryId"), res.getString("cat.description"), res.getBoolean("cat.isActive"));
                    p = new Product(res.getInt("pro.productId"), res.getString("pro.name"), res.getString("pro.nutrientInfo"), res.getString("pro.warnings"), res.getDouble("pro.unitPrice"), res.getBoolean("pro.isActive"));
                    r = getRecipeByProductId(p.getProductId());
                    p.setRecipe(r);
                    p.setCategory(c);
                    products.add(p);
//                pro = new Product();
//                pro.setName(res.getString("name"));
//                pro.setNutrientInfo(res.getString("nutrientInfo"));
//                pro.setWarnings(res.getString("warnings"));
//                pro.setUnitPrice(res.getDouble("unitPrice"));
//                pro.setIsActive(res.getBoolean("isActive"));
                } catch (ArgumentException ex) {

                }

            }

        } catch (SQLException ex) {
            System.out.println("SQL ERROR:" + ex.getMessage());
        }

        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        boolean flag = false;

        if (conn == null) {
            return flag;
        }

        try {
            conn.setAutoCommit(flag);

            //adding a recipe
            flag = addNewRecipe(product.getRecipe());

            if (flag) {

                //adding product
                ps = conn.prepareStatement("INSERT INTO product (productId, name, unitPrice, warnings, nutrientInfo, image, recipeId, category_id, isActive) values(?,?,?,?,?,?,?,?, ?)");
                ps.setInt(1, product.getProductId());
                ps.setString(2, product.getName());
                ps.setDouble(3, product.getUnitPrice());
                ps.setString(4, product.getWarnings());
                ps.setString(5, product.getNutrientInfo());
                ps.setString(6, product.getImage());
                ps.setInt(7, recipeId);
                ps.setInt(8, product.getCategory().getCategoryId());
                ps.setBoolean(9, product.isIsActive());

                if (ps.executeUpdate() > 0) {
                    flag = true;
                    conn.commit();

                } else {
                    flag = false;
                    conn.rollback();
                }

            } else {
                conn.rollback();
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            try {
                flag = false;
                conn.rollback();
            } catch (SQLException ex1) {
                System.out.println("Error: " + ex1.getMessage());
            }
        } finally {

            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Error :" + ex.getMessage());
            }

        }

        return flag;
    }

    @Override
    public boolean updateProduct(Product product) {
        boolean flag = false;

        if (conn == null) {
            return flag;
        }

        try {
            ps = conn.prepareStatement("UPDATE product "
                    + "SET name=? ,nutrientInfo=? ,warnings=? ,unitPrice=? where productId=?");
            ps.setString(1, product.getName());
            ps.setString(2, product.getNutrientInfo());
            ps.setString(3, product.getWarnings());
            ps.setDouble(4, product.getUnitPrice());
            ps.setInt(5, product.getProductId());

            flag = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Err:" + ex.getMessage());
        }

        return flag;
    }

    private byte[] readImageBytes(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        byte[] imageData = new byte[(int) imageFile.length()];

        try (FileInputStream fileInputStream = new FileInputStream(imageFile)) {
            fileInputStream.read(imageData);
        }

        return imageData;
    }

    @Override
    public boolean deleteProductById(int productId, boolean active) {
        boolean flag = false;

        if (conn == null) {
            return flag;
        }

        try {
            ps = conn.prepareStatement("UPDATE product SET isActive=? where productId=?");
            ps.setBoolean(1, active);
            ps.setInt(2, productId);

            flag = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error :" + ex.getMessage());
        }

        return flag;
    }

    public Recipe getRecipeByProductId(int productId) {
        Recipe rec = null;

        if (conn == null) {
            return rec;
        }

        try {
            ps = conn.prepareStatement("SELECT r.recipeId,r.name,r.description from recipe r,product a "
                    + "where a.productId=? "
                    + "AND a.recipeId = r.recipeId");
            ps.setInt(1, productId);

            ResultSet res = ps.executeQuery();
            if (res.next()) {
                rec = new Recipe();
                rec.setName(res.getString("r.name"));
                rec.setDescription(res.getString("r.description"));
                rec.setRecipeId(res.getInt("recipeId"));
                rec.setIngredients(getAllRecipeIngredient(rec.getRecipeId()));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rec;
    }

    public Recipe getRecipeById(int recipeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean addNewRecipe(Recipe recipe) throws SQLException {
        boolean flag = false;

        ps = conn.prepareStatement("INSERT INTO recipe VALUES(null,?,?)");
        // ps.setInt(1, recipe.getRecipeId());
        ps.setString(1, recipe.getName());
        ps.setString(2, recipe.getDescription());

        if (ps.executeUpdate() > 0) {
            recipeId = getRecipeId();

            flag = storeAllRecipeIngredients(recipe.getIngredients(), recipeId);

        }

        return flag;

    }

    private boolean storeAllRecipeIngredients(HashMap<Ingredient, QuantityUnit> ingredients, int recipeId) throws SQLException {
        boolean flag = true;
        int cnt = 0;
        Iterator<Ingredient> iter = ingredients.keySet().iterator();
        Ingredient key;

        while (flag && iter.hasNext()) {
            key = iter.next();
            ps = conn.prepareStatement("INSERT INTO recipe_ingredient(recipe_Id,ingredient_id,unitId,quantity) VALUES(?,?,?,?)");
            ps.setInt(1, recipeId);
            ps.setInt(2, key.getIngredientId());
            ps.setInt(3, ingredients.get(key).getUnit().ordinal());
            ps.setDouble(4, ingredients.get(key).getQty());

            flag = ps.executeUpdate() > 0;

        }

        return flag;
    }

    private int getRecipeId() throws SQLException {

        ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
        ResultSet rs = ps.executeQuery();
        int lastInsertId = -1;
        if (rs.next()) {
            lastInsertId = rs.getInt(1);
            System.out.println("Last insertVal:" + lastInsertId);
        }
        return lastInsertId;
    }

    public boolean updateRecipe(Recipe recipe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        DBManager dbm = new DBManager("jdbc:mysql://localhost:3306/", "bakery?useSSL=false", "root", "root", "com.mysql.cj.jdbc.Driver");
        dbm.getConnection();

        //  System.out.println(ProductDaoImpl.getInstance(dbm.getConnection()).getAllProduct());
//        try {
//            HashMap<Ingredient, QuantityUnit> mapList = new HashMap<>();
//            
//            mapList.put(new Ingredient(1, "floor", 10, 20, true, Unit.KG), new QuantityUnit(10.0, Unit.CUP));
//            mapList.put(new Ingredient(2, "Sugar", 10, 20, true, Unit.KG), new QuantityUnit(2, Unit.CUP));
//            
//            System.out.println(ProductDaoImpl.getInstance(dbm.getConnection()).addProduct(new Product(4, "Blueberry Muffin", "jsc", "contains ...", 10.0, new Category("Muffins", 2, "freshly baked", true), new Recipe(1, "Blueberry Bliss Muffins", "yummie", mapList), true)));
//        } catch (ArgumentException ex) {
//            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//           System.out.println(ProductDaoImpl.getInstance(dbm.getConnection()).getProductById(5).qtysOfIngredient());
//           ProductDaoImpl.getInstance(dbm.getConnection()).getAllProduct().size();


    Product pro = new Product(4, "Blueberry bliss Muffin", "jsc", "contains ...", 10.0,  true);
    
        System.out.println(ProductDaoImpl.getInstance(dbm.getConnection()).updateProduct(pro));
    
    }

    private HashMap<Ingredient, QuantityUnit> getAllRecipeIngredient(int recipeId) throws SQLException {
        HashMap<Ingredient, QuantityUnit> rec = null;

        if (conn == null) {
            return rec;
        }

        ps = conn.prepareStatement("SELECT "
                + "i.ingredientId,i.name,i.stock,i.minStockOnHand, i.unitId,i.isActive, "
                + " r.quantity,r.unitId "
                + "FROM ingredient i, recipe_ingredient r "
                + "WHERE r.recipe_Id = ? "
                + "AND i.ingredientId = r.ingredient_Id ");
        ps.setInt(1, recipeId);

        ResultSet res = ps.executeQuery();
        rec = new HashMap<>();
        Ingredient ingred;
        QuantityUnit qty;
        while (res.next()) {
            ingred = new Ingredient();
            ingred.setIngredientId(res.getInt("i.ingredientId"));
            ingred.setStock(res.getInt("i.stock"));
            ingred.setMinStockOnHand(res.getInt("i.minStockOnHand"));
            ingred.setIsActive(res.getBoolean("i.isActive"));
            ingred.setName(res.getString("i.name"));
            ingred.setUnit(Unit.values()[res.getInt("i.unitId")]);
            qty = new QuantityUnit();
            qty.setQty(res.getDouble("r.quantity"));
            qty.setUnit(Unit.values()[res.getInt("r.unitId")]);
            rec.put(ingred, qty);

        }

        return rec;

    }

}
