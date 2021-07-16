package com.kamildeen.inventorymanagement.service.kafka;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class Consumer {


    @KafkaListener(topics="product-order", groupId="order-group")
    public JSONObject consumeFromTopic(String message) {
        JSONObject jsonObject = new JSONObject(message);

        return jsonObject;
        //System.out.println("Consumed message "+message);
    }


}
