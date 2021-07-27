package com.kamildeen.inventorymanagement.reporting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kamildeen.inventorymanagement.model.CustomerDTO;
import com.kamildeen.inventorymanagement.model.OrderSearchDTO;
import com.kamildeen.inventorymanagement.model.ProductOrder;
import com.kamildeen.inventorymanagement.reporting.service.OrderCreationService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@RestController
@RequestMapping("/api/inventory-management/kafka-order")
@AllArgsConstructor
public class KafkaOrderController {

    private final OrderCreationService orderCreationService;

    @PostMapping("/create-order")
    public CompletableFuture<ResponseEntity> createOrder(@RequestBody CustomerDTO customerDTO) {

        return orderCreationService.createOrder(customerDTO.getPhone())
                .<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleCreateOrderFailure);
                //ResponseEntity.status(HttpStatus.OK).body(orderCreationService.createOrder(customerDTO.getPhone()));
    }


    @PostMapping("/get-orders")
    public ResponseEntity<List<ProductOrder>> getOrders(@RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd/MM/yyyy") Date startDate,
                                                        @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd/MM/yyyy") Date endDate,
                                                        @RequestBody OrderSearchDTO orderSearchDTO){

        return ResponseEntity.status(HttpStatus.OK).body(orderCreationService.getOrders(orderSearchDTO.getPhoneNumber(),
               startDate, endDate));
    }

    private static Function<Throwable, ResponseEntity> handleCreateOrderFailure = throwable -> {
        System.out.println("Unable to create order");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };
}

