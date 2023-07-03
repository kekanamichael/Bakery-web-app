/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.service;

import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Product;
import java.util.List;

/**
 *
 * @author STUDIO 18
 */
public interface ProductService {
    
    boolean addProductNew(Product product);
    List<Product> getAllProducts();
    Product getProductById(int prodId);
    boolean deactivateProduct(int prodId, boolean active) throws ArithmeticException;
    List<Product> getAllActiveProducts();
    boolean updateProduct(Product product)throws ArgumentException;
}
