package com.kamildeen.inventorymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemDTO {

    private Long id;
    private Product product;
    private int quantity;


    public CartItemDTO(Cart cart) {
        this.setId(cart.getId());
        this.setProduct(cart.getProduct());
        this.setQuantity(cart.getQuantity());
    }
}
