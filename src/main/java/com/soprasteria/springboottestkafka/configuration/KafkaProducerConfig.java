package com.soprasteria.springboottestkafka.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {
	
	//CONFIGURAZIONE DEL PRODUTTORE
	
	//IL CAMPO CHE MEMORIZZA L'URL DEL BOOTSTRAP SERVER
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	
	//LE CONFIGURAZIONI DI CUI HA BISOGNO LA PRODUCER FACTORY
	//LE CHIAVI SONO STRINGHE, MENTRE I VALORI POSSONO ESSERE QUALSIASI
	//OGGETTO
	public Map<String, Object> producerConfiguration() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return properties;
	}
	
	//LA PRODUCER FACTORY SI OCCUPA DI CREARE LE ISTANZE DEI PRODUCER
	//LA CHIAVE SARA' IN QUESTO CASO UNA STRINGA E IL VALORE DA INVIARE
	//SARA' UNA STRINGA
	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfiguration());
	}
	
	//...PER INVIARE I MESSAGGI ABBIAMO BISOGNO DI UN KAFKA TEMPLATE
	//IL QUALE RICEVE UNA PRODUCER FACTORY, ISTANZIATA DA SPRING COME BEAN E FORNITA
	//AL METODO TRAMITE LA DEPENDENCY INJECTION
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
		return new KafkaTemplate<>(producerFactory);
	}

}
