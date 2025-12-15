package org.radon.shopyorder.events.application.port.in;

import org.radon.shopyorder.events.domain.model.RollBackInventoryEvent;

import java.util.List;

public interface SendInventoryRollBackEventUseCase {
    void sendRollBack(RollBackInventoryEvent event);
}
