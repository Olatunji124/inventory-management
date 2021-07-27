package com.kamildeen.inventorymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    @SequenceGenerator(name = "customer_id_sequence", sequenceName = "customer_id_sequence")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_sequence")
    private Long id;
    @Column(name = "firstname", nullable = false)
    private String firstName;
    @Column(name = "lastname", nullable = false)
    private String lastName;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "email")
    private  String email;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<ProductOrder> order;

    public Customer(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

}
