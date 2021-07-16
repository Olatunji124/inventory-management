package com.kamildeen.inventorymanagement.repository;

import com.kamildeen.inventorymanagement.model.Customer;
import com.kamildeen.inventorymanagement.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<ProductOrder, Long> {

    List<ProductOrder> findAllByCustomerOrderByOrderDateDesc(Customer customer);
}
