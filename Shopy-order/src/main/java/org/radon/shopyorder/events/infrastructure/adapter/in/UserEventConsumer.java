package org.radon.shopyorder.events.infrastructure.adapter.in;

import org.radon.shopyorder.cart.application.port.in.CreateCartUseCase;
import org.radon.shopyorder.events.domain.model.UserRegisteredEvent;
import org.radon.shopyorder.shared.aop.annotation.EventConsumer;
import org.springframework.kafka.annotation.KafkaListener;

@EventConsumer
public class UserEventConsumer {

    private final CreateCartUseCase createCartUseCase;

    public UserEventConsumer(CreateCartUseCase createCartUseCase) {
        this.createCartUseCase = createCartUseCase;
    }

    @KafkaListener(
            topics = "user.registered",
            groupId = "user-register-group"
    )
    public void receiveUserRegistered(UserRegisteredEvent userRegisteredEvent) {
        createCartUseCase.createCart(userRegisteredEvent.getUserId());
    }



}
