package com.kamildeen.inventorymanagement.reporting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kamildeen.inventorymanagement.model.CustomerDTO;
import com.kamildeen.inventorymanagement.model.ProductOrder;
import com.kamildeen.inventorymanagement.reporting.service.OrderCreationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-management/kafka-order")
@AllArgsConstructor
public class KafkaOrderController {

    private final OrderCreationService orderCreationService;

    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestBody CustomerDTO customerDTO) throws JsonProcessingException {

        return ResponseEntity.status(HttpStatus.OK).body(orderCreationService.createOrder(customerDTO.getPhone()));
    }


    @PostMapping("/get-orders")
    public ResponseEntity<List<ProductOrder>> getOrders(@RequestBody CustomerDTO customerDTO) throws JsonProcessingException {

        return ResponseEntity.status(HttpStatus.OK).body(orderCreationService.getOrders(customerDTO.getPhone()));
    }
}
