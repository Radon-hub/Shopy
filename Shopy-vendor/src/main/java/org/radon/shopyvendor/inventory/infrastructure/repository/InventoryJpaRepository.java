package org.radon.shopyvendor.inventory.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryJpaRepository extends JpaRepository<InventoryEntity, Long> {
    InventoryEntity getInventoryEntitiesById(Long id);

    InventoryEntity getInventoryEntitiesByProduct_IdAndProduct_VendorEntity_Id(Long productId, Long productVendorEntityId);
}
