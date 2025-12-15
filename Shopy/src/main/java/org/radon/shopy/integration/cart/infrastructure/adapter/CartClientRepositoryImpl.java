package org.radon.shopy.integration.cart.infrastructure.adapter;

import com.radon.grpc.ItemForCart;
import jakarta.transaction.Transactional;
import lombok.val;
import org.radon.grpc.GetCartRequest;
import org.radon.shopy.integration.cart.application.port.out.CartClientRepository;
import org.radon.shopy.integration.cart.infrastructure.repository.CartGrpcClient;
import org.radon.shopy.integration.cart.infrastructure.repository.InventoryGrpcClient;
import org.radon.shopy.integration.cart.model.Cart;
import org.radon.shopy.integration.cart.model.CartItem;
import org.radon.shopy.integration.cart.presentation.controller.mappers.CartGrpcMappers;
import org.radon.shopy.integration.cart.presentation.controller.mappers.InventoryGrpcMappers;
import org.radon.shopy.integration.cart.presentation.dto.CheckForAddOrRemoveDto;
import org.radon.shopy.user.application.port.in.GetUserUseCase;
import org.springframework.stereotype.Repository;

@Repository
public class CartClientRepositoryImpl implements CartClientRepository {

    private final InventoryGrpcClient inventoryGrpcClient;
    private final CartGrpcClient cartGrpcClient;
    private final GetUserUseCase getUserUseCase;

    public CartClientRepositoryImpl(InventoryGrpcClient inventoryGrpcClient, CartGrpcClient cartGrpcClient, GetUserUseCase getUserUseCase) {
        this.inventoryGrpcClient = inventoryGrpcClient;
        this.cartGrpcClient = cartGrpcClient;
        this.getUserUseCase = getUserUseCase;
    }

    @Override
    public Cart getCart() {
        val user = getUserUseCase.getUser();

        return CartGrpcMappers.toCart(
                cartGrpcClient.getCart(
                        GetCartRequest.newBuilder()
                                .setUserId(user.getId())
                                .build())
        );
    }

    @Transactional
    @Override
    public CartItem checkForAddItemToCart(CheckForAddOrRemoveDto request) {
        return InventoryGrpcMappers.toCartItem(
                inventoryGrpcClient.checkInventoryForAdd(
                        ItemForCart.newBuilder()
                                .setProductId(request.getProductId())
                                .setCartId(request.getCartId())
                                .setVendorId(request.getVendorId())
                                .build()
                )
        );
    }

    @Override
    public CartItem checkForRemoveItemFromCart(CheckForAddOrRemoveDto request) {
        return InventoryGrpcMappers.toCartItem(
                inventoryGrpcClient.checkInventoryForRemove(
                        ItemForCart.newBuilder()
                                .setProductId(request.getProductId())
                                .setCartId(request.getCartId())
                                .setVendorId(request.getVendorId())
                                .build()
                )
        );
    }

}
