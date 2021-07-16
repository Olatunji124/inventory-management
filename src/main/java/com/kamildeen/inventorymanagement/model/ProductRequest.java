package com.kamildeen.inventorymanagement.model;

import lombok.*;

@Data
@NoArgsConstructor
public class ProductRequest {

    private long id;
    private double price;
    private double availableQuantity;

}
