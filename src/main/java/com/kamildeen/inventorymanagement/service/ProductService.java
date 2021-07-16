package com.kamildeen.inventorymanagement.service;

import com.kamildeen.inventorymanagement.model.Product;
import com.kamildeen.inventorymanagement.model.ProductRequest;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    Product getById(Long productId);

    Product getByName(String productName);

    List<Product> getAllProducts();

    int updateProduct(Long id, ProductRequest product);
}
