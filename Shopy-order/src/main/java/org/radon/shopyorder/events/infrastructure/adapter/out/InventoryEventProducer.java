package org.radon.shopyorder.events.infrastructure.adapter.out;

import org.radon.shopyorder.events.domain.model.RollBackInventoryEvent;
import org.radon.shopyorder.shared.aop.annotation.EventProducer;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

@EventProducer
public class InventoryEventProducer {

    private final KafkaTemplate<String, RollBackInventoryEvent> kafkaTemplate;


    public InventoryEventProducer(KafkaTemplate<String, RollBackInventoryEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendRollBack(RollBackInventoryEvent event) {
        kafkaTemplate.send("rollback.inventory",String.valueOf(event.hashCode()), event);
    }

}
