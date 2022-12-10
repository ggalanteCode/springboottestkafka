package com.soprasteria.springboottestkafka.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
	
	//IL CONSUMER RIMANE IN ASCOLTO E RICEVE GLI STREAM DI EVENTI
	//DI UN TOPIC...
	//...QUINDI CON IL CONTROLLER (PER MEZZO DI UN KAFKA TEMPLATE) 
	//PUBBLICHIAMO UN MESSAGGIO, E IL CONSUMER LO LEGGE
	
	@KafkaListener(topics = "firstTopic", groupId = "com.soprasteria")
	void listener(String data) {
		System.out.println("Il listener ha ricevuto: " + data);
	}

}
