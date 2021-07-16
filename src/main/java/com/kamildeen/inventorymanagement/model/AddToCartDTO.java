package com.kamildeen.inventorymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddToCartDTO {

    private int quantity;
    private String productId;
    private String customerName;
    private String customerPhone;
}
