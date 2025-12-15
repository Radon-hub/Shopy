package org.radon.shopy.events.infrastructure.adapter.out;

import org.radon.shopy.events.domain.model.UserRegisteredEvent;
import org.radon.shopy.shared.aop.annotation.EventProducer;
import org.springframework.kafka.core.KafkaTemplate;

@EventProducer
public class UserEventProducer {

    private final KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate;

    public UserEventProducer(KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserRegisteredEvent(UserRegisteredEvent model) {
        kafkaTemplate.send("user.registered",model.getUserId().toString(), model);
    }


}
