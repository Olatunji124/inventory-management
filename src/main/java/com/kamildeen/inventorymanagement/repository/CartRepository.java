package com.kamildeen.inventorymanagement.repository;

import com.kamildeen.inventorymanagement.model.Cart;
import com.kamildeen.inventorymanagement.model.Customer;
import com.kamildeen.inventorymanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByCustomerOrderByCreatedDateDesc(Customer customer);

    Cart findByCustomerAndAndProduct(Customer customer, Product product);

    void deleteByCustomer(Customer customer);
}
