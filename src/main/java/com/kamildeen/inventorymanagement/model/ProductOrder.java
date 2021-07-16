package com.kamildeen.inventorymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Setter
public class ProductOrder {

    @SequenceGenerator(name = "order_id_sequence", sequenceName = "order_id_sequence", initialValue = 1000)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_sequence")
    private Long id;

    private Date orderDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItem;

    private double totalAmount;

    public ProductOrder(Customer customer, double totalAmount) {
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.orderDate = new Date();
    }

}
