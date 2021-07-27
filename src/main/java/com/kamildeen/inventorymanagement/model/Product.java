package com.kamildeen.inventorymanagement.model;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {


    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", initialValue = 10000, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Long id;
    @NotNull
    private String name;
    private double price;
    @NotNull
    private int availableQuantity;
    private String description;

}
