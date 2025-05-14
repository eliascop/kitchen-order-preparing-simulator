package br.com.kitchen.orderpreparingsimulator.app.config;

import br.com.kitchen.orderpreparingsimulator.app.dto.OrderDTO;
import br.com.kitchen.orderpreparingsimulator.app.producer.OrderProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaProducerConfig {

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public KafkaProducerConfig(KafkaTemplate<String, OrderDTO> kafkaOrderTemplate) {
        this.kafkaTemplate = kafkaOrderTemplate;
    }

    @Bean
    public OrderProducer<OrderDTO> kafkaProducer() {
        return new OrderProducer<>(kafkaTemplate, "new-order");
    }

}
