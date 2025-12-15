package org.radon.shopyvendor.inventory.application.port.in;

import org.radon.shopyvendor.inventory.domain.model.Inventory;
import org.radon.shopyvendor.inventory.domain.model.QuantityState;

public interface UpdateProductQuantityUseCase {
    Inventory updateProductQuantity(Long inventoryId, QuantityState quantityState);
}
