package br.com.kitchen.orderpreparingsimulator.app.consumer;

import br.com.kitchen.orderpreparingsimulator.app.dto.OrderDTO;
import br.com.kitchen.orderpreparingsimulator.app.service.OrderPreparingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OrderPreparingService orderPreparingService;

    public OrderConsumer(OrderPreparingService orderPreparingService) {
        this.orderPreparingService = orderPreparingService;
    }

    @KafkaListener(topics = "new-order")
    public void listen(String strOrder) {
        try {
            OrderDTO orderDTO = objectMapper.readValue(strOrder, OrderDTO.class);
            System.out.println("Novo pedido: "+orderDTO.toString());
            if(orderDTO.getId() != null && !orderDTO.getStatus().isEmpty()){
                orderPreparingService.prepare(orderDTO);
            } else {
                System.err.println("Invalid message: "+strOrder);
            }
        } catch (JsonProcessingException e) {
            System.err.println("Poorly formatted message: " + strOrder);
        }
    }
}
