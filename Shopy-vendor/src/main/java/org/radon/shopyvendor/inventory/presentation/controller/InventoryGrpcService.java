package org.radon.shopyvendor.inventory.presentation.controller;

import com.radon.grpc.InventoryGrpc;
import com.radon.grpc.ItemForCart;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import jakarta.transaction.Transactional;
import lombok.val;
import org.radon.shopyvendor.integration.cart.application.port.in.AddItemToCartUseCase;
import org.radon.shopyvendor.integration.cart.application.port.in.RemoveItemFromCartUseCase;
import org.radon.shopyvendor.integration.cart.presentation.dto.AddItemToCartRequestDto;
import org.radon.shopyvendor.integration.cart.presentation.dto.RemoveItemFromCartRequestDto;
import org.radon.shopyvendor.inventory.application.port.in.UpdateProductQuantityUseCase;
import org.radon.shopyvendor.inventory.domain.model.QuantityState;
import org.radon.shopyvendor.product.infrastructure.repository.ProductJpaRepository;
import org.radon.shopyvendor.shared.aop.exceptionHandling.ProductNotFoundException;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class InventoryGrpcService extends InventoryGrpc.InventoryImplBase {

    private final ProductJpaRepository productJpaRepository;
    private final AddItemToCartUseCase addItemToCartUseCase;
    private final RemoveItemFromCartUseCase removeItemFromCartUseCase;
    private final UpdateProductQuantityUseCase updateProductQuantityUseCase;

    public InventoryGrpcService(ProductJpaRepository productJpaRepository, AddItemToCartUseCase addItemToCartUseCase, RemoveItemFromCartUseCase removeItemFromCartUseCase, UpdateProductQuantityUseCase updateProductQuantityUseCase) {
        this.productJpaRepository = productJpaRepository;
        this.addItemToCartUseCase = addItemToCartUseCase;
        this.removeItemFromCartUseCase = removeItemFromCartUseCase;
        this.updateProductQuantityUseCase = updateProductQuantityUseCase;
    }

    @Transactional
    @Override
    public void checkInventoryForAdd(ItemForCart request, StreamObserver<ItemForCart> responseObserver) {

        val productPersistence = productJpaRepository.findProductEntityById(request.getProductId());

        if(productPersistence.isEmpty()){
            throw Status.NOT_FOUND
                    .withDescription("Product not found!")
                    .asRuntimeException();
        }
        val product = productPersistence.get();

        val inventory = product.getInventory();

        val vendor = product.getVendorEntity();

        if(inventory.getQuantity() < 1){
            throw Status.FAILED_PRECONDITION
                    .withDescription("Inventory is empty!")
                    .asRuntimeException();
        }

        val req = new AddItemToCartRequestDto(
                request.getCartId(),
                request.getVendorId(),
                request.getProductId(),
                product.getPrice()
        );

        val addItemToCartRequest = addItemToCartUseCase.addItemToCart(req);

        updateProductQuantityUseCase.updateProductQuantity(inventory.getId(), QuantityState.REMOVE);

        responseObserver.onNext(
                ItemForCart.newBuilder()
                        .setCartId(addItemToCartRequest.getId().toString())
                        .setVendorId(vendor.getId())
                        .setProductId(product.getId())
                        .setPrice(product.getPrice())
                        .build()
        );

        responseObserver.onCompleted();

    }

    @Transactional
    @Override
    public void checkInventoryForRemove(ItemForCart request, StreamObserver<ItemForCart> responseObserver) {


        val productPersistence = productJpaRepository.findProductEntityById(request.getProductId());

        if(productPersistence.isEmpty()){
            throw Status.NOT_FOUND
                    .withDescription("Product not found!")
                    .asRuntimeException();
        }
        val product = productPersistence.get();

        val inventory = product.getInventory();

        val vendor = product.getVendorEntity();

        val req = new RemoveItemFromCartRequestDto(
                request.getCartId(),
                request.getProductId()
        );

        val removeItemFromCartRequest = removeItemFromCartUseCase.removeItemFromCart(req);

        updateProductQuantityUseCase.updateProductQuantity(inventory.getId(), QuantityState.ADD);
        responseObserver.onNext(
                ItemForCart.newBuilder()
                        .setCartId(removeItemFromCartRequest.getId().toString())
                        .setVendorId(vendor.getId())
                        .setProductId(product.getId())
                        .setPrice(product.getPrice())
                        .build()
        );

        responseObserver.onCompleted();
    }
}
