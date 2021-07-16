package com.kamildeen.inventorymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderItemDTO {

    private LocalDateTime createdDate;
    private List<OrderItem> orderItem;
    private double totalAmount;

    public OrderItemDTO(ProductOrder productOrder) {
        this.createdDate = LocalDateTime.now();
        this.orderItem = productOrder.getOrderItem();
        this.totalAmount = productOrder.getTotalAmount();
    }
}
