package com.kamildeen.inventorymanagement.controller;


import com.kamildeen.inventorymanagement.model.Product;
import com.kamildeen.inventorymanagement.model.ProductRequest;
import com.kamildeen.inventorymanagement.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-management/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(path = "/add-product")
    public ResponseEntity<Product> addProducts(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.OK).body(productService.addProduct(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getById(id));
    }

//    @GetMapping("/{productName}")
//    public ResponseEntity<Product> getProductByName(@PathVariable String productName){
//        return ResponseEntity.status(HttpStatus.OK).body(productService.getByName(productName));
//    }

    @GetMapping("/product-list")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @PostMapping("/update-product/{id}")
    public ResponseEntity<Integer> updateProduct( @PathVariable("id") Long id, @RequestBody ProductRequest product){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, product));
    }
}
