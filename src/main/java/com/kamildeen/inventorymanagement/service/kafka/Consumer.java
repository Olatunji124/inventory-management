package com.kamildeen.inventorymanagement.service.kafka;

import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class Consumer {

    @KafkaListener(topics="product-order", groupId="order-group")
    public void consumeFromTopic(String message) {

        System.out.println("Consumed message "+message);
    }


}
