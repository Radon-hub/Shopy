package org.radon.shopyorder.events.application.service;

import org.radon.shopyorder.events.application.port.in.SendInventoryRollBackEventUseCase;
import org.radon.shopyorder.events.application.port.out.EventRepository;
import org.radon.shopyorder.events.domain.model.RollBackInventoryEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements SendInventoryRollBackEventUseCase {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void sendRollBack(RollBackInventoryEvent event) {
        eventRepository.sendRollBack(event);
    }
}
