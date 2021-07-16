package com.kamildeen.inventorymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class PlaceOrderDTO {

    private Long id;
    private Customer customer;
    private double totalAmount;
    private Date orderDate;
}
