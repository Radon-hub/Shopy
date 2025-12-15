package org.radon.shopyorder.order.infrastructure.repository;

import org.radon.shopyorder.order.domain.model.OrderStatus;
import org.radon.shopyorder.order.infrastructure.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
    Optional<OrderEntity> findOrderEntitiesByIdempotencyKey(String idempotencyKey);

    Optional<OrderEntity> findOrderEntitiesByIdAndUserId(UUID id, Long userId);

    List<OrderEntity> findOrderEntitiesByUserId(Long userId);

    Optional<OrderEntity> findOrderEntitiesByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);

    List<OrderEntity> findOrderEntitiesByUserIdAndOrderStatusIn(Long userId, Collection<OrderStatus> orderStatuses);

    Optional<OrderEntity> findOrderEntitiesByOrderNumber(String orderNumber);

    List<OrderEntity> findAllByOrderStatusInAndCreatedAtBefore(Collection<OrderStatus> orderStatuses, LocalDateTime createdAtBefore);

    OrderEntity getOrderEntitiesByOrderNumber(String orderNumber);

    Optional<OrderEntity> findOrderEntitiesByOrderNumberAndUserId(String orderNumber, Long userId);

    Optional<OrderEntity> findOrderEntitiesByOrderNumberAndUserIdAndOrderStatusIn(String orderNumber, Long userId, Collection<OrderStatus> orderStatuses);
}
