package org.radon.shopyvendor.inventory.application.port.in;

import org.radon.shopyvendor.events.domain.model.RollBackEventModel;
import org.radon.shopyvendor.events.domain.model.RollBackInventoryEvent;
import org.radon.shopyvendor.inventory.domain.model.Inventory;

public interface RollBackInventoryUseCase {
    Inventory rollBackInventory(RollBackEventModel model);
}
