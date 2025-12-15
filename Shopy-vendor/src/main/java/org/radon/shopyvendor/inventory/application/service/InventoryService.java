package org.radon.shopyvendor.inventory.application.service;

import org.radon.shopyvendor.events.domain.model.RollBackEventModel;
import org.radon.shopyvendor.events.domain.model.RollBackInventoryEvent;
import org.radon.shopyvendor.inventory.application.port.in.RollBackInventoryUseCase;
import org.radon.shopyvendor.inventory.application.port.in.UpdateProductQuantityUseCase;
import org.radon.shopyvendor.inventory.application.port.out.InventoryRepository;
import org.radon.shopyvendor.inventory.domain.model.Inventory;
import org.radon.shopyvendor.inventory.domain.model.QuantityState;
import org.radon.shopyvendor.product.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService implements UpdateProductQuantityUseCase, RollBackInventoryUseCase {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory updateProductQuantity(Long inventoryId, QuantityState quantityState) {
        return inventoryRepository.updateProductQuantity(inventoryId,quantityState);
    }

    @Override
    public Inventory rollBackInventory(RollBackEventModel model) {
        return inventoryRepository.rollBackInventory(model);
    }
}
