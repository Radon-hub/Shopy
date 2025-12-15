package org.radon.shopy.integration.cart.infrastructure.repository;

import com.radon.grpc.InventoryGrpc;
import com.radon.grpc.ItemForCart;
import jakarta.transaction.Transactional;
import lombok.val;
import org.radon.shopy.shared.aop.exceptionHandling.model.IllegalArgumentException;
import org.springframework.stereotype.Component;

@Component
public class InventoryGrpcClient {

    private final InventoryGrpc.InventoryBlockingStub inventoryBlockingStub;

    public InventoryGrpcClient(InventoryGrpc.InventoryBlockingStub inventoryBlockingStub) {
        this.inventoryBlockingStub = inventoryBlockingStub;
    }

    @Transactional
    public ItemForCart checkInventoryForAdd(ItemForCart itemForCart){
        return inventoryBlockingStub.checkInventoryForAdd(itemForCart);
    }

    @Transactional
    public ItemForCart checkInventoryForRemove(ItemForCart itemForCart){
        return inventoryBlockingStub.checkInventoryForRemove(itemForCart);
    }

}
