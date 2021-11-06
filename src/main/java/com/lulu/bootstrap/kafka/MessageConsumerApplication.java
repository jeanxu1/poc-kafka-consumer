package com.lulu.bootstrap.kafka;

import com.lulu.bootstrap.kafka.processor.MessageProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;

@SpringBootApplication
@EnableBinding(MessageProcessor.class)
@EnableSchemaRegistryClient
public class MessageConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageConsumerApplication.class, args);
	}

}
