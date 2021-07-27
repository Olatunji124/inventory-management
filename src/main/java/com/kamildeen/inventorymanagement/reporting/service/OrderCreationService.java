package com.kamildeen.inventorymanagement.reporting.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamildeen.inventorymanagement.model.*;
import com.kamildeen.inventorymanagement.repository.CustomerRepository;
import com.kamildeen.inventorymanagement.repository.OrderRepository;
import com.kamildeen.inventorymanagement.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class OrderCreationService {

    private final CustomerRepository customerRepository;
    private final CartService cartService;
    private final ReportProducer producer;
    private final OrderRepository orderRepository;


    @Async
    public synchronized CompletableFuture<String> createOrder(String customerPhone) {
        CartDTO cartDTO = cartService.getAllCartItems(customerPhone);
        PlaceOrderDTO placeOrderDTO = new PlaceOrderDTO();
        placeOrderDTO.setCustomer(cartDTO.getCustomer());
        placeOrderDTO.setTotalAmount(cartDTO.getTotalAmount());
        placeOrderDTO.setOrderDate(new Date());
        System.out.println("==========Order placed 1");
        String order = convertToString(placeOrderDTO);
        System.out.println("==========Order placed");
        producer.publishToTopic(order);
        System.out.println("==========Order published to kafka");

        return CompletableFuture.completedFuture(order);
    }

    public List<ProductOrder> getOrders(String customerPhone, Date startDate, Date endDate) {
        Customer customer = getCustomerByPhone(customerPhone);
        List<ProductOrder> orderList = orderRepository.findAllByCustomerAndOrderDateBetween(customer,startDate, endDate);
        return orderList;
    }


    private Customer getCustomerByPhone(String customerPhone) {
        return customerRepository.findByPhone(customerPhone)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer with phone " +customerPhone+  " does not exist"));

    }

    private String convertToString(PlaceOrderDTO placeOrderDTO)  {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(placeOrderDTO);
            System.out.println(jsonString);
            return jsonString;
        } catch (JsonProcessingException ex){
            ex.getMessage();
            ex.printStackTrace();
            return "Error";
        }
    }
}
