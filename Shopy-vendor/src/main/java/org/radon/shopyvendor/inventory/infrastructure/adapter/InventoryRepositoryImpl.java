package org.radon.shopyvendor.inventory.infrastructure.adapter;

import jakarta.transaction.Transactional;
import lombok.val;
import org.radon.shopyvendor.events.domain.model.RollBackEventModel;
import org.radon.shopyvendor.events.domain.model.RollBackInventoryEvent;
import org.radon.shopyvendor.inventory.application.port.out.InventoryRepository;
import org.radon.shopyvendor.inventory.domain.model.Inventory;
import org.radon.shopyvendor.inventory.domain.model.QuantityState;
import org.radon.shopyvendor.inventory.infrastructure.adapter.mappers.InventoryMappers;
import org.radon.shopyvendor.inventory.infrastructure.repository.InventoryEntity;
import org.radon.shopyvendor.inventory.infrastructure.repository.InventoryJpaRepository;
import org.radon.shopyvendor.product.domain.model.Product;
import org.radon.shopyvendor.shared.aop.exceptionHandling.ProductNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InventoryRepositoryImpl implements InventoryRepository {

    private final InventoryJpaRepository inventoryJpaRepository;

    public InventoryRepositoryImpl(InventoryJpaRepository inventoryJpaRepository) {
        this.inventoryJpaRepository = inventoryJpaRepository;
    }

    @Transactional
    @Override
    public Inventory updateProductQuantity(Long inventoryId,QuantityState quantityState) {

        InventoryEntity inventory = inventoryJpaRepository.getInventoryEntitiesById(inventoryId);

        switch(quantityState){
            case ADD:
                inventory.setQuantity(inventory.getQuantity()+1);
                break;
            case REMOVE:
                inventory.setQuantity(inventory.getQuantity()-1);
                break;
        }

        inventory.setUpdatedAt(LocalDateTime.now().toString());

        return InventoryMappers.toInventory(inventory);
    }

    @Override
    public Inventory rollBackInventory(RollBackEventModel model) {
        InventoryEntity inventory = inventoryJpaRepository.getInventoryEntitiesByProduct_IdAndProduct_VendorEntity_Id(model.getProductId(), model.getVendorId());
        inventory.setQuantity(inventory.getQuantity()+model.getQuantity());
        inventoryJpaRepository.save(inventory);
        return InventoryMappers.toInventory(inventory);
    }

}
