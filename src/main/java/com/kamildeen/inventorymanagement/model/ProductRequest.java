package com.kamildeen.inventorymanagement.model;

import lombok.*;

@Data
public class ProductRequest {

    private long id;
    private double price;
    private int availableQuantity;

}
