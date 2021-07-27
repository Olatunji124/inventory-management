package com.kamildeen.inventorymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CartResponseDTO {

    private Customer customer;
    private List<Cart> cart ;

}
