package org.radon.shopyorder.payment.infrastructure.adapter;

import jakarta.transaction.Transactional;
import lombok.val;
import org.radon.shopyorder.cart.infrastructure.repository.CartItemsJpaRepository;
import org.radon.shopyorder.cart.infrastructure.repository.entity.CartItemEntity;
import org.radon.shopyorder.order.application.port.out.OrderRepository;
import org.radon.shopyorder.order.domain.model.OrderStatus;
import org.radon.shopyorder.order.infrastructure.adapter.mapper.OrderMappers;
import org.radon.shopyorder.order.infrastructure.repository.OrderJpaRepository;
import org.radon.shopyorder.order.infrastructure.repository.entity.OrderEntity;
import org.radon.shopyorder.order.infrastructure.repository.entity.OrderItemEntity;
import org.radon.shopyorder.payment.application.port.out.PaymentRepository;
import org.radon.shopyorder.payment.domain.model.Payment;
import org.radon.shopyorder.payment.domain.model.PaymentStatus;
import org.radon.shopyorder.shared.aop.exceptionHandling.IncorrectPaymentID;
import org.radon.shopyorder.shared.aop.exceptionHandling.OrderNotFound;
import org.radon.shopyorder.shared.aop.exceptionHandling.OrderNotReadyForPayment;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    private final OrderJpaRepository orderRepository;
    private final CartItemsJpaRepository cartItemsJpaRepository;
    private final CacheManager cacheManager;

    public PaymentRepositoryImpl(OrderJpaRepository orderRepository,CacheManager cacheManager, CartItemsJpaRepository cartItemsJpaRepository) {
        this.orderRepository = orderRepository;
        this.cartItemsJpaRepository = cartItemsJpaRepository;
        this.cacheManager = cacheManager;
    }

    @Transactional
    @Override
    public Payment payment(String orderNumber,String paymentId, PaymentStatus paymentStatus) {

        OrderEntity orderEntity = orderRepository.getOrderEntitiesByOrderNumber(orderNumber);

        if(orderEntity==null){
            throw new OrderNotFound();
        }

        if(orderEntity.getOrderStatus() != OrderStatus.PENDING_PAYMENT){
            throw new OrderNotReadyForPayment();
        }

        if(!paymentId.equals(orderEntity.getPaymentId())){
            orderEntity.setOrderStatus(OrderStatus.CREATED);
            orderEntity.setPaymentId(UUID.randomUUID().toString());
            throw new IncorrectPaymentID();
        }

        if(paymentStatus==PaymentStatus.FAILED){
            orderEntity.setOrderStatus(OrderStatus.FAILED);
        }else{
            orderEntity.setOrderStatus(OrderStatus.CONFIRMED);

            List<CartItemEntity> cartItems = cartItemsJpaRepository.findAllByCartEntity_UserIdAndProductIdIn(
                    orderEntity.getUserId(),
                    orderEntity.getItems().stream().map(OrderItemEntity::getProductId).toList()
            );

            Objects.requireNonNull(cacheManager.getCache("cart")).evict(orderEntity.getUserId());

            cartItemsJpaRepository.deleteAll(cartItems);

        }

        orderEntity.setUpdatedAt(LocalDateTime.now());

        orderRepository.save(orderEntity);

        Objects.requireNonNull(cacheManager.getCache("order")).put(orderEntity.getOrderNumber(),OrderMappers.toOrder(orderEntity));

        return new Payment(
                OrderMappers.toOrder(orderEntity),
                paymentStatus
        );
    }
}
