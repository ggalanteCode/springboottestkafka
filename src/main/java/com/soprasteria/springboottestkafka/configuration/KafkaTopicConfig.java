package com.soprasteria.springboottestkafka.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
	
	//QUESTA E' LA CLASSE CHE SI OCCUPA DI CREARE I TOPIC
	
	@Bean
	public NewTopic firstTopic() {
		return TopicBuilder.name("firstTopic").build();
	}

}
