package org.radon.shopyorder.cart.infrastructure.repository;

import org.radon.shopyorder.cart.domain.model.CartItem;
import org.radon.shopyorder.cart.infrastructure.repository.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartItemsJpaRepository extends JpaRepository<CartItemEntity, UUID> {
    Optional<CartItemEntity> findCartItemEntityByProductIdAndCartEntityId(Long productId, UUID cartEntityId);
    List<CartItemEntity> findAllByCartEntity_UserIdAndProductIdIn(Long cartEntityUserId, Collection<Long> productIds);

    void deleteCartItemEntitiesById(UUID id);

    void deleteCartItemEntitiesByProductIdAndVendorIdAndCartEntity_UserId(Long productId, Long vendorId, Long cartEntityUserId);
}
