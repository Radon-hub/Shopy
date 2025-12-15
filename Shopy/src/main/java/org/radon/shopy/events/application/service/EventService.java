package org.radon.shopy.events.application.service;

import org.radon.shopy.events.application.port.in.SendUserRegisteredEventUseCase;
import org.radon.shopy.events.application.port.out.EventRepository;
import org.radon.shopy.events.domain.model.UserRegisteredEvent;
import org.springframework.stereotype.Service;

@Service
public class EventService implements SendUserRegisteredEventUseCase {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void sendEvent(UserRegisteredEvent model) {
        eventRepository.sendUserRegisteredEvent(model);
    }
}
