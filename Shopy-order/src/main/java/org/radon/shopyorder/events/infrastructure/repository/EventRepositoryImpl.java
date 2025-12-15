package org.radon.shopyorder.events.infrastructure.repository;

import org.radon.shopyorder.events.application.port.out.EventRepository;
import org.radon.shopyorder.events.domain.model.RollBackInventoryEvent;
import org.radon.shopyorder.events.infrastructure.adapter.out.InventoryEventProducer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventRepositoryImpl implements EventRepository {

    private final InventoryEventProducer inventoryEventProducer;

    public EventRepositoryImpl(InventoryEventProducer inventoryEventProducer) {
        this.inventoryEventProducer = inventoryEventProducer;
    }

    @Override
    public void sendRollBack(RollBackInventoryEvent event) {
        inventoryEventProducer.sendRollBack(event);
    }
}
