package org.radon.shopy.integration.cart.presentation.controller.mappers;

import com.radon.grpc.ItemForCart;
import org.radon.shopy.integration.cart.model.CartItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InventoryGrpcMappers {
    public static CartItem toCartItem(ItemForCart itemForCart){
        return new CartItem(
                null,
                itemForCart.getProductId(),
                itemForCart.getVendorId(),
                1,
                BigDecimal.valueOf(itemForCart.getPrice()),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
