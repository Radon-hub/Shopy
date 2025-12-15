package org.radon.shopy.events.infrastructure.adapter;

import org.radon.shopy.events.application.port.in.SendUserRegisteredEventUseCase;
import org.radon.shopy.events.application.port.out.EventRepository;
import org.radon.shopy.events.domain.model.UserRegisteredEvent;
import org.radon.shopy.events.infrastructure.adapter.out.UserEventProducer;
import org.springframework.stereotype.Component;

@Component
public class EventRepositoryImpl implements EventRepository {

    private final UserEventProducer userEventProducer;

    public EventRepositoryImpl(UserEventProducer userEventProducer) {
        this.userEventProducer = userEventProducer;
    }

    @Override
    public void sendUserRegisteredEvent(UserRegisteredEvent model) {
        userEventProducer.sendUserRegisteredEvent(model);
    }

}
