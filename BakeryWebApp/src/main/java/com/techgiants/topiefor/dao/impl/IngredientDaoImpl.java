package com.techgiants.topiefor.dao.impl;

import com.techgiants.topiefor.dao.IngredientDao;
import com.techgiants.topiefor.model.Ingredient;
import com.techgiants.topiefor.db.DBManager;
import com.techgiants.topiefor.model.Unit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientDaoImpl implements IngredientDao {

    private static IngredientDaoImpl ingredientDaoImpl;
    private Connection con = null;
    private PreparedStatement ps;

    private IngredientDaoImpl(Connection con) {
        this.con = con;
    }

    public static IngredientDaoImpl getInstance(Connection con) {
        if (ingredientDaoImpl == null) {
            ingredientDaoImpl = new IngredientDaoImpl(con);
        }
        return ingredientDaoImpl;
    }

    @Override
    public boolean addIngredient(Ingredient ingredient) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }
        try {
            ps = con.prepareStatement("INSERT INTO INGREDIENT (name, stock, minStockOnHand, isActive, unitId) VALUES(?,?,?,?,?)");
            ps.setString(1, ingredient.getName());
            ps.setInt(2, 0);
            ps.setDouble(3, ingredient.getMinStockOnHand());
            ps.setBoolean(4, true);
            ps.setInt(5, ingredient.getUnit().ordinal());
            ps.setBoolean(4, true);
            if (ps.executeUpdate() > 0) {
                retVal = true;
            }
        } catch (SQLException e) {
            System.out.println("Cannot add Ingredient: " + e.getMessage());
        }

        return retVal;
    }

    @Override
    public boolean updateIngredient(Ingredient ingredient) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }
        try {
            ps = con.prepareStatement("UPDATE INGREDIENT SET name = ?, minStockOnHand=? WHERE ingredientId = ?");
            ps.setString(1, ingredient.getName());
            //ps.setDouble(2, ingredient.getStock());
            ps.setDouble(2, ingredient.getMinStockOnHand());
            //ps.setBoolean(3, true);
            ps.setInt(3, ingredient.getIngredientId());
            if (ps.executeUpdate() > 0) {
                retVal = true;
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
        return retVal;
    }

        @Override
    public boolean deleteIngredient(int ingredientId, boolean active) {
        boolean retVal = false;
        if (con == null) {
            return retVal;
        }
        try {
            ps = con.prepareStatement("UPDATE ingredient SET isActive=? WHERE ingredientId=?");
            ps.setBoolean(1, active);
            ps.setInt(2, ingredientId);
            if (ps.executeUpdate() > 0) {
                retVal = true;
            }
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
        return retVal;
    }
    @Override
    public Ingredient getIngredientById(int ingredientId) {
        Ingredient ingredient = null;

        if (con == null) {
            return ingredient;
        }
        try {
            ps = con.prepareStatement("SELECT ingredientId, name, stock, minStockOnHand, unitId, isActive FROM Ingredient WHERE ingredientId = ?");
            ps.setInt(1, ingredientId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ingredient = new Ingredient(rs.getInt("ingredientId"), rs.getString("name"), rs.getDouble("stock"), rs.getDouble("minStockOnHand"), rs.getBoolean("isActive"),  Unit.values()[rs.getInt("unitId")]);
                ingredient.setIngredientId(ingredientId);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("SQL ERROR: " + ex.getMessage());
        }
        return ingredient;
    }

    @Override
    public List<Ingredient> getAllIngredients() {
         List<Ingredient> ingredientList = new ArrayList();
        if (con == null) {
            return ingredientList;
        }
        try {
            ps = con.prepareStatement("SELECT ingredientId, name, stock, minStockOnHand, unitId, isActive FROM Ingredient");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ingredientList.add(new Ingredient(rs.getInt("ingredientId"), rs.getString("name"), rs.getDouble("stock"), rs.getDouble("minStockOnHand"), rs.getBoolean("isActive"), Unit.values()[rs.getInt("unitId")]));
            }
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
        return ingredientList;
    }

    public static void main(String[] args) {
        DBManager dbm = new DBManager("jdbc:mysql://localhost:3306/", "bakery?useSSL=false", "root", "root", "com.mysql.cj.jdbc.Driver");
        //new IngredientDaoImpl(dbm.getConnection()).addIngredient(new Ingredient(1,"egg",6,5,true));
        System.out.println(new IngredientDaoImpl(dbm.getConnection()).getIngredientById(4));
    }

    @Override
    public double getStockByIngredientId(int ingredId) {
        double retVal = -1;
        if (con == null) {
            return retVal;
        }

        try {
            ps = con.prepareStatement("SELECT stock FROM ingredient WHERE ingredientId=?");

            ps.setInt(1, ingredId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                retVal = rs.getInt("stock");

            }

        } catch (SQLException ex) {
            System.out.println("Error SQL: "+ex.getMessage());
        }

        return retVal;
    }

    @Override
    public boolean updateStockByIngredientId(int ingredId, double stock) {

        boolean retVal = true;
        if (con == null) {
            return retVal;
        }
        try {
            ps = con.prepareStatement("UPDATE INGREDIENT SET stock=? WHERE ingredientId = ?");
            ps.setDouble(1, stock);
            ps.setInt(2, ingredId);
            retVal = (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            System.out.println("Error SQL: "+ex.getMessage());
        }
        return retVal;
    }
}
