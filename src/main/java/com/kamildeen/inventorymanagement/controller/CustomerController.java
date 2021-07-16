package com.kamildeen.inventorymanagement.controller;


import com.kamildeen.inventorymanagement.model.CustomerDTO;
import com.kamildeen.inventorymanagement.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory-management/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add-customer")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.addCustomer(customerDTO));
    }
}
