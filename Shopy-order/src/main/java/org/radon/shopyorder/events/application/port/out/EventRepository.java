package org.radon.shopyorder.events.application.port.out;

import org.radon.shopyorder.events.domain.model.RollBackInventoryEvent;

import java.util.List;

public interface EventRepository {
    void sendRollBack(RollBackInventoryEvent event);

}
