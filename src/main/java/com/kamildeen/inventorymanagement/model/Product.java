package com.kamildeen.inventorymanagement.model;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Product {


    @SequenceGenerator(name = "product_sequence", initialValue = 10000,
            allocationSize = 1, sequenceName = "product_sequence")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Long id;
    @NotNull
    private String name;
    private double price;
    @NotNull
    private Double availableQuantity;
    private String description;

}
