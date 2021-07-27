package com.kamildeen.inventorymanagement.repository;

import com.kamildeen.inventorymanagement.model.Customer;
import com.kamildeen.inventorymanagement.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<ProductOrder, Long> {

    //@Query(value = "SELECT * FROM ProductOrder p WHERE p.customer=?1A AND p.orderDate >= :startDate AND p.orderDate <= :endDate", nativeQuery = true)
    List<ProductOrder> findAllByCustomerAndOrderDateBetween(Customer customer,
                                                             Date startDate,
                                                            Date endDate);

    List<ProductOrder> findAllByCustomerOrderByOrderDateDesc(Customer customer);
}
