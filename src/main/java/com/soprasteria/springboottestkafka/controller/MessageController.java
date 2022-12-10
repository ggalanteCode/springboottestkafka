package com.soprasteria.springboottestkafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.springboottestkafka.request.MessageRequest;

@RestController
@RequestMapping("/kafka/messages")
public class MessageController {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@PostMapping("/pubblica")
	public void pubblica(@RequestBody MessageRequest request) {
		kafkaTemplate.send("firstTopic", request.messaggio());
	}

}
