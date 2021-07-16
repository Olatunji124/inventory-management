package com.kamildeen.inventorymanagement.service.kafka;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class Producer {

    public static final String TOPIC = "product-order";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publishToTopic(String message){//(List<ProductOrder> message) {
        System.out.println("Publishing to topic "+TOPIC);
        this.kafkaTemplate.send(TOPIC, message);
    }
}
