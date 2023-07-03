/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.techgiants.topiefor.dao;

import com.techgiants.topiefor.model.Product;
import java.util.List;

/**
 *
 * @author STUDIO 18
 */
public interface ProductDao {
    
    Product getProductById(int productId);
    List<Product> getAllProduct();
    List<Product> getAllProductByCategoryId(int categoryId);
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProductById(int productId, boolean active);
    List<Product> getAllActiveProducts();
    
    
}
