package com.kamildeen.inventorymanagement.repository;

import com.kamildeen.inventorymanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String productName);
}
