package com.kamildeen.inventorymanagement.report.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamildeen.inventorymanagement.model.CartDTO;
import com.kamildeen.inventorymanagement.model.CartItemDTO;
import com.kamildeen.inventorymanagement.model.OrderItem;
import com.kamildeen.inventorymanagement.model.ProductOrder;
import com.kamildeen.inventorymanagement.repository.OrderRepository;
import com.kamildeen.inventorymanagement.service.CartService;
import com.kamildeen.inventorymanagement.service.OrderItemServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ReportConsumer {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final OrderItemServiceImpl orderItemService;

    @KafkaListener(topics="kafka-order", groupId="kafka-order-group")
    public void consumeFromTopic(String message) throws JsonProcessingException {


        ObjectMapper mapper = new ObjectMapper();
        ProductOrder order = mapper.readValue(message, ProductOrder.class);
        ProductOrder newOrder = orderRepository.save(order);
        String customerPhone = newOrder.getCustomer().getPhone();

        CartDTO cartDTO = cartService.getAllCartItems(customerPhone);
        saveOrderItems(newOrder, customerPhone, cartDTO, orderItemService, cartService);
        System.out.println("Consumed message "+message);
    }

    public static void saveOrderItems(ProductOrder newOrder, String customerPhone, CartDTO cartDTO, OrderItemServiceImpl orderItemService, CartService cartService) {
        List<CartItemDTO> cartItemDTOList = cartDTO.getCartItems();
        for (CartItemDTO cartItemDTO : cartItemDTOList) {
            OrderItem orderItem = new OrderItem(
                    newOrder,
                    cartItemDTO.getProduct(),
                    cartItemDTO.getQuantity(),
                    cartItemDTO.getProduct().getPrice());
            orderItemService.addOrderedProduct(orderItem);
        }
        cartService.deleteAllCartItems(customerPhone);
    }

}
