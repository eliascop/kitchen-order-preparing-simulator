package br.com.kitchen.orderpreparingsimulator.app.consumer;

import br.com.kitchen.orderpreparingsimulator.app.dto.OrderDTO;
import br.com.kitchen.orderpreparingsimulator.app.service.OrderPreparingService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    private final OrderPreparingService orderPreparingService;

    public OrderConsumer(OrderPreparingService orderPreparingService) {
        this.orderPreparingService = orderPreparingService;
    }

    @KafkaListener(topics = "new-order")
    public void listen(OrderDTO order) {
        orderPreparingService.prepare(order);
    }
}
