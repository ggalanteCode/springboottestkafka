package com.soprasteria.springboottestkafka.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
public class KafkaConsumerConfig {

	// IL CAMPO CHE MEMORIZZA L'URL DEL BOOTSTRAP SERVER
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	// LE CONFIGURAZIONI DI CUI HA BISOGNO LA CONSUMER FACTORY
	// LE CHIAVI SONO STRINGHE, MENTRE I VALORI POSSONO ESSERE QUALSIASI
	// OGGETTO
	public Map<String, Object> consumerConfiguration() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return properties;
	}

	// LA CONSUMER FACTORY SI OCCUPA DI CREARE LE ISTANZE DEI CONSUMER
	// LA CHIAVE SARA' IN QUESTO CASO UNA STRINGA E IL VALORE DA INVIARE
	// SARA' UNA STRINGA
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfiguration());
	}
	
	//IL LISTENER CONTAINER FACTORY RICEVE TUTTI I MESSAGGI DA TUTTI I
	//TOPIC SU UN SINGOLO THREAD
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> listenerContainerfactory(ConsumerFactory<String, String> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
