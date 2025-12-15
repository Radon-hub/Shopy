package org.radon.shopyvendor.inventory.application.port.out;

import org.radon.shopyvendor.events.domain.model.RollBackEventModel;
import org.radon.shopyvendor.events.domain.model.RollBackInventoryEvent;
import org.radon.shopyvendor.inventory.domain.model.Inventory;
import org.radon.shopyvendor.inventory.domain.model.QuantityState;

public interface InventoryRepository {
    Inventory updateProductQuantity(Long inventoryId, QuantityState quantityState);
    Inventory rollBackInventory(RollBackEventModel model);
}
