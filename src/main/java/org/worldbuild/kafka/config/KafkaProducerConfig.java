package org.worldbuild.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.worldbuild.kafka.modal.UserDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${kafka.bootstrap.address}")
    private String bootstrapAddress;

    @Bean
    public Map<String,Object> configMap(){
        final Map<String,Object> configMap=new HashMap<String,Object>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return  configMap;
    }

    @Bean
    public ProducerFactory<String, UserDto> producerFactory(){
        return new DefaultKafkaProducerFactory<String, UserDto>(configMap());
    }

    @Bean
    public KafkaTemplate<String, UserDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
