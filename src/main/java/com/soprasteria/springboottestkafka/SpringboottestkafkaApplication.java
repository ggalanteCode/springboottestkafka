package com.soprasteria.springboottestkafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SpringboottestkafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringboottestkafkaApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> template) {
		return args -> {
			template.send("firstTopic", "Hello, World!!! in Kafka");
		};
	}

}
