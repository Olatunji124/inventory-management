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
public class OrderItem {

    @SequenceGenerator(name = "order_item_sequence", sequenceName = "order_item_sequence", initialValue = 1000)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_sequence")
    private Long id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-DD-YYYY")
    private Date createdDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private ProductOrder order;

    @OneToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    private Product product;

    public OrderItem(ProductOrder order, Product product, int quantity,  double price) {
        this.quantity = quantity;
        this.price = price;
        this.createdDate = new Date();
        this.order = order;
        this.product = product;
    }

    public OrderItem(int quantity, double price, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }
}
