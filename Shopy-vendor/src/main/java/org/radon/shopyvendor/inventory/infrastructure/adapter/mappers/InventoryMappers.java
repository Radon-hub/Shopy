package org.radon.shopyvendor.inventory.infrastructure.adapter.mappers;

import org.radon.shopyvendor.inventory.domain.model.Inventory;
import org.radon.shopyvendor.inventory.infrastructure.repository.InventoryEntity;
import org.radon.shopyvendor.product.domain.model.Product;
import org.radon.shopyvendor.product.infrastructure.adapter.mapper.ProductMapper;
import org.radon.shopyvendor.vendor.infrastructure.adapter.mapper.VendorMappers;

import java.util.ArrayList;
import java.util.List;

public class InventoryMappers {


    public static List<Product> toProductList(List<InventoryEntity> inventories) {
        List<Product> products = new ArrayList<>();

        inventories.forEach(inventory -> {
            products.add(new Product(
                    inventory.getProduct().getId(),
                    inventory.getProduct().getName(),
                    inventory.getProduct().getDescription(),
                    inventory.getProduct().getDetails(),
                    inventory.getProduct().getPrice(),
                    String.valueOf(inventory.getQuantity())
            ));
        });

        return products;
    }

    public static Inventory toInventory(InventoryEntity inventoryEntity) {
        return new Inventory(
                inventoryEntity.getId(),
                ProductMapper.toProduct(inventoryEntity.getProduct()),
                inventoryEntity.getQuantity(),
                inventoryEntity.getCreatedAt(),
                inventoryEntity.getUpdatedAt()
        );
    }

    public static InventoryEntity toInventoryEntity(Inventory inventory) {
        return new InventoryEntity(
                inventory.getId(),
                ProductMapper.toProductEntity(inventory.getProduct()),
                inventory.getQuantity(),
                inventory.getCreatedAt(),
                inventory.getUpdatedAt()
        );
    }
}
