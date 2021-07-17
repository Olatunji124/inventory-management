package com.kamildeen.inventorymanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamildeen.inventorymanagement.model.CustomerDTO;
import com.kamildeen.inventorymanagement.model.ProductOrder;
import com.kamildeen.inventorymanagement.service.implementation.OrderServiceImpl;
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



    @PostMapping("/order-product")
    public ResponseEntity<String> orderProduct(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.placeOrder(customerDTO.getPhone()));
    }


    @GetMapping("get-order/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrder(id));

    }


    @PostMapping("/get-orders")
    public ResponseEntity<List<ProductOrder>> getAllOrders(@RequestBody CustomerDTO customerDTO) throws JsonProcessingException {
        List<ProductOrder> order = orderService.listOrders(customerDTO.getPhone());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(order);

        //publish customer's order to kafka
        producer.publishToTopic(jsonString);
        return ResponseEntity.status(HttpStatus.OK).body(orderService.listOrders(customerDTO.getPhone()));
    }


}
