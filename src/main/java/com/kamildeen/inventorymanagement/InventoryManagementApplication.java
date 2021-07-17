package com.kamildeen.inventorymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
public class InventoryManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(InventoryManagementApplication.class, args);


	}

//	@Bean
//	ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
//			ConsumerFactory<String, String> consumerFactory) {
//		ConcurrentKafkaListenerContainerFactory<String, String> factory =
//				new ConcurrentKafkaListenerContainerFactory<>();
//		factory.setConsumerFactory(consumerFactory);
//		factory.setConcurrency(4);
//		factory.getContainerProperties().setPollTimeout(10_000);
//		factory.getContainerProperties().setConsumerTaskExecutor(execC());
//		//factory.getContainerProperties().setListenerTaskExecutor(execL());
//		return factory;
//	}
//
//	@Bean
//	public AsyncListenableTaskExecutor execC() {
//		ThreadPoolTaskExecutor tpte = new ThreadPoolTaskExecutor();
//		tpte.setCorePoolSize(10);
//		return tpte;
//	}
//
//	@Bean
//	public AsyncListenableTaskExecutor execL() {
//		ThreadPoolTaskExecutor thread = new ThreadPoolTaskExecutor();
//		thread.setCorePoolSize(10);
//		return thread;
//	}


}
