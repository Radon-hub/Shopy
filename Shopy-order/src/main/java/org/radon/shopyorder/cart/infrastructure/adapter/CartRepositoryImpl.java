package org.radon.shopyorder.cart.infrastructure.adapter;

import io.grpc.Status;
import jakarta.transaction.Transactional;
import lombok.val;
import org.radon.shopyorder.cart.application.port.out.CartRepository;
import org.radon.shopyorder.cart.domain.model.Cart;
import org.radon.shopyorder.cart.domain.model.CartItem;
import org.radon.shopyorder.cart.domain.model.CartStatus;
import org.radon.shopyorder.cart.infrastructure.adapter.mappers.CartMapper;
import org.radon.shopyorder.cart.infrastructure.repository.CartItemsJpaRepository;
import org.radon.shopyorder.cart.infrastructure.repository.CartJpaRepository;
import org.radon.shopyorder.cart.infrastructure.repository.entity.CartEntity;
import org.radon.shopyorder.cart.infrastructure.repository.entity.CartItemEntity;
import org.radon.shopyorder.shared.aop.exceptionHandling.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CartRepositoryImpl implements CartRepository {

    private final CartJpaRepository cartJpaRepository;
    private final CartItemsJpaRepository cartItemsJpaRepository;

    public CartRepositoryImpl(CartJpaRepository cartJpaRepository, CartItemsJpaRepository cartItemsJpaRepository) {
        this.cartJpaRepository = cartJpaRepository;
        this.cartItemsJpaRepository = cartItemsJpaRepository;
    }


    @Override
    public List<Cart> getAllCarts() {
        val carts =  cartJpaRepository.findAll();

        if(carts.isEmpty()) {
            throw new NoCartFoundException();
        }

        return carts.stream().map(CartMapper::fromCartEntity).toList();
    }

    @Override
    public Cart getCartById(UUID id) {

        Optional<CartEntity> cartEntity = cartJpaRepository.findById(id);

        if(cartEntity.isEmpty()) {
            throw new CartNotFoundException(id.toString());
        }

        return CartMapper.fromCartEntity(cartEntity.get());
    }

    @Override
    public Cart getCartByUserId(Long userId) {

        Optional<CartEntity> cartEntity = cartJpaRepository.findCartEntitiesByUserId(userId);

        if(cartEntity.isEmpty()) {
            throw new CartNotFoundException(userId.toString());
        }

        return CartMapper.fromCartEntity(cartEntity.get());
    }

    @Override
    public List<CartItem> getCartItemsByCartId(UUID cartId) {

        return List.of();
    }

    @Override
    public Cart createCart(Long userId) {
        Optional<CartEntity> cartEntity = cartJpaRepository.findCartEntitiesByUserId(userId);

        if(cartEntity.isPresent()) {
            throw new CartExistException(userId.toString());
        }

        CartEntity createdCartEntity = cartJpaRepository.save(new CartEntity(
                userId,
                CartStatus.ACTIVE
        ));


        return CartMapper.fromCartEntity(createdCartEntity);
    }

    @Transactional
    @Override
    public Cart changeCartStatus(Cart cart) {
        CartEntity cartEntity = cartJpaRepository.getCartEntitiesById(cart.getId());

        if(cartEntity == null) {
            throw new CartNotFoundException(cart.getId().toString());
        }

        cartEntity.setCartStatus(cart.getCartStatus());
        cartEntity.setUpdated_at(cart.getUpdated_at());

        return CartMapper.fromCartEntity(cartEntity);
    }

    @Override
    public Cart deleteCart(UUID cartId,Long userId) {
        Optional<CartEntity> cartEntity = cartJpaRepository.findById(cartId);

        if(cartEntity.isEmpty()) {
            throw new CartNotFoundException(cartId.toString());
        }

        if(!cartEntity.get().getUserId().equals(userId)) {
            throw new CartAccessDeniedException(userId.toString());
        }

        cartJpaRepository.delete(cartEntity.get());

        return CartMapper.fromCartEntity(cartEntity.get());
    }

    @Transactional
    @Override
    public Cart addItemToCart(UUID cartId, CartItem cartItem) {

        Optional<CartItemEntity> itemOpt = cartItemsJpaRepository
                .findCartItemEntityByProductIdAndCartEntityId(cartItem.getProductId(), cartId);

        Optional<CartEntity> cart = cartJpaRepository.findById(cartId);

        if(cart.isEmpty()) {
            throw Status.NOT_FOUND
                    .withDescription("Cart not found!")
                    .asRuntimeException();
        }

        CartEntity cartEntity = cart.get();

        if(cartEntity.getCartStatus() == CartStatus.ABANDONED) {
            throw Status.FAILED_PRECONDITION
                    .withDescription("Cart is banned!")
                    .asRuntimeException();
        }

        if (itemOpt.isEmpty()) {
            CartItemEntity newItem = new CartItemEntity(
                    cartItem.getProductId(),
                    cartItem.getVendorId(),
                    cartEntity,
                    cartItem.getQuantity(),
                    cartItem.getPrice(),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
            cartItemsJpaRepository.save(newItem);

            cartEntity.getCartItems().add(newItem);

        } else {
            CartItemEntity existedItem = itemOpt.get();
            existedItem.setQuantity(existedItem.getQuantity() + cartItem.getQuantity());
            existedItem.setUpdated_at(LocalDateTime.now());
        }

        return CartMapper.fromCartEntity(cartEntity);
    }


    @Transactional
    @Override
    public Cart removeItemFromCart(UUID cartId, Long productId) {

        Optional<CartItemEntity> itemPresent = cartItemsJpaRepository
                .findCartItemEntityByProductIdAndCartEntityId(productId, cartId);

        if(itemPresent.isEmpty()) {
            throw Status.NOT_FOUND
                    .withDescription("Cart item not found!")
                    .asRuntimeException();
        }

        CartItemEntity item = itemPresent.get();

        if (item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
            item.setUpdated_at(LocalDateTime.now());
        } else {
            cartItemsJpaRepository.delete(item);
            // Flush immediately to make sure the deletion is executed
            cartItemsJpaRepository.flush();
        }

        return CartMapper.fromCartEntity(item.getCartEntity());
    }
}
