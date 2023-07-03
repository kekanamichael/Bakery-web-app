/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.service.impl;

import com.techgiants.topiefor.dao.ProductDao;
import com.techgiants.topiefor.dao.impl.ProductDaoImpl;
import com.techgiants.topiefor.db.DBManager;
import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Product;
import com.techgiants.topiefor.service.ProductService;
import java.util.List;

/**
 *
 * @author STUDIO 18
 */
public class ProductServiceImpl implements ProductService {

    private static ProductServiceImpl impl;
    private ProductDao dao;

    private ProductServiceImpl(ProductDao dao) {
        this.dao = dao;
    }

    public static ProductServiceImpl getInstance(ProductDao dao) {
        if (impl == null) {
            impl = new ProductServiceImpl(dao);
        }

        return impl;
    }

    @Override
    public boolean addProductNew(Product product) {
        boolean flag = false;

        if (product == null) {
            return flag;
        }

        return dao.addProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return dao.getAllProduct();
    }

    public static void main(String[] args) {

        DBManager dbm = new DBManager("jdbc:mysql://localhost:3306/", "bakery?useSSL=false", "mie", "mie", "com.mysql.cj.jdbc.Driver");
        dbm.getConnection();

        ProductServiceImpl.getInstance(ProductDaoImpl.getInstance(dbm.getConnection()))
                .getAllProducts().forEach(System.out::println);

    }

    @Override
    public Product getProductById(int prodId) {
        return dao.getProductById(prodId);
    }

    @Override
    public boolean deactivateProduct(int prodId, boolean active) throws ArithmeticException {
        if (prodId < 0) {
            throw new ArithmeticException("Invalid id");
        }

        return dao.deleteProductById(prodId, active);
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return dao.getAllActiveProducts();
    }

    @Override
    public boolean updateProduct(Product product) throws ArgumentException {
        if(product==null)
            throw new ArgumentException("Cannot update a null ingredient");
        return dao.updateProduct(product);
    }
}
