package com.kamildeen.inventorymanagement.reporting.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamildeen.inventorymanagement.model.*;
import com.kamildeen.inventorymanagement.repository.OrderRepository;
import com.kamildeen.inventorymanagement.repository.ProductRepository;
import com.kamildeen.inventorymanagement.service.CartService;
import com.kamildeen.inventorymanagement.service.implementation.OrderItemServiceImpl;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ReportConsumer {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final OrderItemServiceImpl orderItemService;
    private final ProductRepository productRepository;

    @KafkaListener(topics="kafka-order", groupId="kafka-order-group")
    public void consumeFromTopic(String message) {

        System.out.println("==========Order Consumed");
        try {


            JSONObject json = new JSONObject(message);
            String customerPhone = json.getJSONObject("customer").optString("phone");

            ObjectMapper mapper = new ObjectMapper();
            ProductOrder order = mapper.readValue(message, ProductOrder.class);
            ProductOrder newOrder = orderRepository.save(order);

            CartDTO cartDTO = cartService.getAllCartItems(customerPhone);
            saveOrderItems(newOrder, customerPhone, cartDTO, orderItemService, cartService, productRepository);
            System.out.println("Consumed message " + message);
        } catch (JsonProcessingException ex){
            ex.printStackTrace();
            ex.getMessage();
        }
    }

    public static void saveOrderItems(ProductOrder newOrder, String customerPhone, CartDTO cartDTO,
                                      OrderItemServiceImpl orderItemService, CartService cartService,
                                      ProductRepository productRepository) {
        List<CartItemDTO> cartItemDTOList = cartDTO.getCartItems();
        for (CartItemDTO cartItemDTO : cartItemDTOList) {
            OrderItem orderItem = new OrderItem(
                    newOrder,
                    cartItemDTO.getProduct(),
                    cartItemDTO.getQuantity(),
                    cartItemDTO.getProduct().getPrice());
            orderItemService.addOrderedProduct(orderItem);

            int stockQty = cartItemDTO.getProduct().getAvailableQuantity();
            int diff = stockQty - cartItemDTO.getQuantity();
            System.out.println("AVAILABLE STOCK "+stockQty + " "+ diff);
            Product product = cartItemDTO.getProduct();
            product.setAvailableQuantity(diff);
            productRepository.save(product);
        }
        cartService.deleteAllCartItems(customerPhone);
    }

}
