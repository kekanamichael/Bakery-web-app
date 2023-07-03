package com.techgiants.topiefor.service.impl;

import com.techgiants.topiefor.dao.IngredientDao;
import com.techgiants.topiefor.dao.Ingredient_OrderDao;
import com.techgiants.topiefor.dao.impl.IngredientDaoImpl;
import com.techgiants.topiefor.dao.impl.Ingredient_OrderDaoImpl;
import com.techgiants.topiefor.db.DBManager;
import com.techgiants.topiefor.model.Ingredient_Order;
import com.techgiants.topiefor.model.Ingredient_OrderItem;
import com.techgiants.topiefor.service.Ingredient_OrderService;
import java.util.List;

public class Ingredient_OrderServiceImpl implements Ingredient_OrderService {

    private Ingredient_OrderDao dao;
    private IngredientDao ingredDao;

    private static Ingredient_OrderServiceImpl impl;

    private Ingredient_OrderServiceImpl(Ingredient_OrderDao dao, IngredientDao ingredDao) {
        this.dao = dao;
        this.ingredDao = ingredDao;
    }

    public static Ingredient_OrderServiceImpl getInstance(Ingredient_OrderDao dao, IngredientDao ingredDao) {

        if (impl == null) {
            impl = new Ingredient_OrderServiceImpl(dao, ingredDao);
        }

        return impl;
    }

    @Override
    public boolean addIngredient_Order(Ingredient_Order order) {
        boolean flag = false;

        if (order == null) {
            return flag;
        }

        return dao.addIngredient_Order(order);
    }

    @Override
    public List<Ingredient_Order> getAllIngredient_Orders() {
        return dao.getAllIngredientsOrder();
    }

    @Override
    public boolean confirmIngredientOrderDelivery(int ingredientOrderId) {
        boolean flag = false;

        if (dao == null || ingredDao == null || ingredientOrderId < 0) {
            return flag;
        }

        if (flag = dao.isOrderDelivered(ingredientOrderId)) {
            return flag;
        }

        //get all the order items 
        List<Ingredient_OrderItem> items = dao.getAllOrderItemsByOrderId(ingredientOrderId);
        int cnt = 0;

        flag = items != null;

        //loop through all the items 
        //on the loop you 
        // - get the current stock of ingredient 
        // - sum up the qty on the order item and the stock
        // - then update stock of that ingredient
        //
        while (flag && cnt < items.size()) {

            double stck = ingredDao.getStockByIngredientId(items.get(cnt).getIngredient().getIngredientId());
            if (flag = stck > -1) {
                stck += items.get(cnt).getQuantity();
                flag = ingredDao.updateStockByIngredientId(items.get(cnt).getIngredient().getIngredientId(), stck);
                cnt++;
            }

        }

        return dao.updateIngredient_Order(ingredientOrderId);
    }

    public static void main(String[] args) {
        DBManager dbm = new DBManager("jdbc:mysql://localhost:3306/", "bakery?useSSL=false", "root", "root", "com.mysql.cj.jdbc.Driver");
        dbm.getConnection();

        Ingredient_OrderServiceImpl.getInstance(Ingredient_OrderDaoImpl.getInstance(dbm.getConnection()),
                IngredientDaoImpl.getInstance(dbm.getConnection()))
                .confirmIngredientOrderDelivery(5);
//                .getAllIngredient_Orders().forEach(System.out::println);
        

    }

}
