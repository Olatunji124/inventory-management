package com.kamildeen.inventorymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class CartDTO {

    private Customer customer;
    private List<CartItemDTO> cartItems;
    private double totalAmount;
}
