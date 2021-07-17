package com.kamildeen.inventorymanagement.service.implementation;

import com.kamildeen.inventorymanagement.model.Product;
import com.kamildeen.inventorymanagement.model.ProductRequest;
import com.kamildeen.inventorymanagement.repository.ProductRepository;
import com.kamildeen.inventorymanagement.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        boolean productExists = productRepository.findById(product.getId()).isPresent();
        if(productExists){
            throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with Id "+  product.getId() + " Already exists");
        }
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Product with Id "+  productId + " does not exist"));
    }

    @Override
    public Product getByName(String productName) {

        return productRepository.findByName(productName).orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Product with name "+  productName + " does not exist"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public int updateProduct(Long id, ProductRequest product) {
        try {
            Product productToUpdate = productRepository.findById(id)
                    .orElseThrow(()-> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Product with Id "+  id + " does not exist"));

            productToUpdate.setAvailableQuantity(product.getAvailableQuantity());
            productToUpdate.setPrice(product.getPrice());
            productRepository.save(productToUpdate);
            return 1;
        } catch (Exception ex){
            ex.getMessage();
            return 0;
        }
    }
}
