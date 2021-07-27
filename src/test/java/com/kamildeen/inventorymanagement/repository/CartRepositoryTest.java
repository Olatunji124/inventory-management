package com.kamildeen.inventorymanagement.repository;

import com.kamildeen.inventorymanagement.model.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    void findAllByCustomerOrderByCreatedDateDesc() {
        //Cart cart = new Cart(null, "");
    }

    @Test
    void findByCustomerAndAndProduct() {
    }

    @Test
    void deleteByCustomer() {
    }
}