package com.kamildeen.inventorymanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamildeen.inventorymanagement.model.CustomerDTO;
import com.kamildeen.inventorymanagement.model.ProductOrder;
import com.kamildeen.inventorymanagement.report.service.OrderCreationService;
import com.kamildeen.inventorymanagement.service.OrderServiceImpl;
import com.kamildeen.inventorymanagement.service.kafka.Producer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-management/order")
@AllArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;
    private final Producer producer;
    private final OrderCreationService orderCreationService;


    @PostMapping("/order-product")
    public ResponseEntity<String> orderProduct(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.placeOrder(customerDTO.getPhone()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrder(id));

    }


    @PostMapping("/get-order")
    public ResponseEntity<List<ProductOrder>> getAllOrders(@RequestBody CustomerDTO customerDTO) throws JsonProcessingException {
        List<ProductOrder> order = orderService.listOrders(customerDTO.getPhone());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(order);
        producer.publishToTopic(jsonString);
        return ResponseEntity.status(HttpStatus.OK).body(orderService.listOrders(customerDTO.getPhone()));
    }

    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestBody CustomerDTO customerDTO) throws JsonProcessingException {

        return ResponseEntity.status(HttpStatus.OK).body(orderCreationService.createOrder(customerDTO.getPhone()));
    }


    @PostMapping("/get-orders")
    public ResponseEntity<List<ProductOrder>> getOrders(@RequestBody CustomerDTO customerDTO) throws JsonProcessingException {

        return ResponseEntity.status(HttpStatus.OK).body(orderCreationService.getOrders(customerDTO.getPhone()));
    }

}
