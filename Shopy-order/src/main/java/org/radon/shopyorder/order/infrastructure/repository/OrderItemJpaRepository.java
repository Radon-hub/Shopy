package org.radon.shopyorder.order.infrastructure.repository;

import org.radon.shopyorder.order.infrastructure.repository.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, UUID> {}
