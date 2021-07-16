package com.kamildeen.inventorymanagement.report.service;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportProducer {

    public static final String TOPIC = "kafka-order";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publishToTopic(String message) {
        System.out.println("Publishing to topic "+TOPIC);
        this.kafkaTemplate.send(TOPIC, message);
    }
}
