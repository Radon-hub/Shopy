package org.radon.shopyorder.cart.infrastructure.repository;

import org.radon.shopyorder.cart.infrastructure.repository.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartJpaRepository extends JpaRepository<CartEntity, UUID> {
    Optional<CartEntity> findCartEntitiesByUserId(Long userId);

    CartEntity getCartEntitiesById(UUID id);

    List<CartEntity> findCartEntitiesById(UUID id);
}
