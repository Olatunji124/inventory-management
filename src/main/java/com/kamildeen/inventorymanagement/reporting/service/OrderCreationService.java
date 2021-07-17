package com.kamildeen.inventorymanagement.reporting.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamildeen.inventorymanagement.model.*;
import com.kamildeen.inventorymanagement.repository.CustomerRepository;
import com.kamildeen.inventorymanagement.repository.OrderRepository;
import com.kamildeen.inventorymanagement.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderCreationService {

    private final CustomerRepository customerRepository;
    private final CartService cartService;
    private final ReportProducer producer;
    private final OrderRepository orderRepository;


    public String createOrder(String customerPhone) throws JsonProcessingException {
        CartDTO cartDTO = cartService.getAllCartItems(customerPhone);
        Customer customer = getCustomerByPhone(customerPhone);
        PlaceOrderDTO placeOrderDTO = new PlaceOrderDTO();
        placeOrderDTO.setCustomer(customer);
        placeOrderDTO.setTotalAmount(cartDTO.getTotalAmount());
        placeOrderDTO.setOrderDate(new Date());

        String order = toString(placeOrderDTO);
        producer.publishToTopic(order);

        return "Order Placed";
    }

    public List<ProductOrder> getOrders(String customerPhone) {
        Customer customer = getCustomerByPhone(customerPhone);
        List<ProductOrder> orderList = orderRepository.findAllByCustomerOrderByBetweenDates(customer);
        return orderList;
    }


    private Customer getCustomerByPhone(String customerPhone) {
        return customerRepository.findByPhone(customerPhone)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer with phone " +customerPhone+  " does not exist"));

    }

    private String toString(PlaceOrderDTO placeOrderDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(placeOrderDTO);
        return jsonString;
    }
}
