package com.kamildeen.inventorymanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Cart {

    @SequenceGenerator(name = "cart_id_sequence", sequenceName = "cart_id_sequence", initialValue = 1000)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_id_sequence")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-DD-YYYY")
    private Date createdDate;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne
    private Product product;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "customer", referencedColumnName = "id")
    private Customer customer;

    private int quantity;

    public Cart(Date createdDate, Product product, Customer customer, int quantity) {
        this.createdDate = createdDate;
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
    }
}
