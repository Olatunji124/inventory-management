package com.kamildeen.inventorymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}
