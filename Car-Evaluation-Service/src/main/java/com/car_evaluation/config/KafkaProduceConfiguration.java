package com.car_evaluation.config;

import com.car_evaluation.constants.AppConstants;
import com.car_evaluation.payload.EmailRequest;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProduceConfiguration {

    @Bean
    public ProducerFactory<String, EmailRequest> producerFactory() {

        Map<String, Object> kafkaProps = new HashMap<>();

        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.KAFKA_HOST);
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(kafkaProps);
    }

    @Bean
    public KafkaTemplate<String, EmailRequest> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
