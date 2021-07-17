package com.kamildeen.inventorymanagement.service.implementation;

import com.kamildeen.inventorymanagement.model.OrderItem;
import com.kamildeen.inventorymanagement.repository.OrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class OrderItemServiceImpl {

    private final OrderItemRepository orderItemRepository;

    public void addOrderedProduct(OrderItem item){
        orderItemRepository.save(item);
    }
}
