package org.radon.shopyvendor.events.infrastructure.adapter.in;

import jakarta.transaction.Transactional;
import org.radon.shopyvendor.events.domain.model.RollBackInventoryEvent;
import org.radon.shopyvendor.inventory.application.port.in.RollBackInventoryUseCase;
import org.radon.shopyvendor.shared.annotation.EventConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import java.util.List;

@EventConsumer
public class RollBackInventoryEventConsumer {

    private final RollBackInventoryUseCase rollBackInventoryUseCase;

    public RollBackInventoryEventConsumer(RollBackInventoryUseCase rollBackInventoryUseCase) {
        this.rollBackInventoryUseCase = rollBackInventoryUseCase;
    }

    @Transactional
    @KafkaListener(
            topics = "rollback.inventory",
            groupId = "shopy-rollback-group"
    )
    public void receiveInventoryRollBack(RollBackInventoryEvent rollBackInventoryEvents) {
        rollBackInventoryEvents.getItems().forEach(rollBackInventoryUseCase::rollBackInventory);
    }



}
