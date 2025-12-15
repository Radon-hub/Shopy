package org.radon.shopyorder.order.infrastructure.adapter;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.radon.shopyorder.cart.infrastructure.repository.CartItemsJpaRepository;
import org.radon.shopyorder.events.application.port.in.SendInventoryRollBackEventUseCase;
import org.radon.shopyorder.events.domain.model.RollBackEventModel;
import org.radon.shopyorder.events.domain.model.RollBackInventoryEvent;
import org.radon.shopyorder.order.domain.model.Order;
import org.radon.shopyorder.order.domain.model.OrderItem;
import org.radon.shopyorder.order.domain.model.OrderStatus;
import org.radon.shopyorder.order.infrastructure.adapter.mapper.OrderMappers;
import org.radon.shopyorder.order.infrastructure.repository.OrderJpaRepository;
import org.radon.shopyorder.order.infrastructure.repository.entity.OrderEntity;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderCheckScheduler {

    private final OrderJpaRepository orderJpaRepository;
    private final CartItemsJpaRepository cartItemsJpaRepository;
    private final CacheManager cacheManager;
    private final SendInventoryRollBackEventUseCase sendInventoryRollBackEventUseCase;
    //This Scheduled job will run every 1 min to ensure all orders will be expired after 15 min
    @Transactional
    @Scheduled(fixedRate = 60000)
    public void checkOrders() {

        System.out.println("OrderCheckScheduler started...");

        List<OrderEntity> orders = orderJpaRepository.findAllByOrderStatusInAndCreatedAtBefore(
                List.of(OrderStatus.PROCESSING,OrderStatus.CREATED,OrderStatus.PENDING_PAYMENT),
                LocalDateTime.now().minusMinutes(15)
        );

        orders.forEach(order -> order.setOrderStatus(OrderStatus.EXPIRED));

        List<OrderItem> orderItemsList = new ArrayList<>();

        orders.forEach(order -> {
            order.getItems().forEach(item -> {
                orderItemsList.add(OrderMappers.toOrderItem(item));
                cartItemsJpaRepository.deleteCartItemEntitiesByProductIdAndVendorIdAndCartEntity_UserId(item.getProductId(), item.getVendorId(), item.getOrderEntity().getUserId());
            });
        });

        orderJpaRepository.saveAll(orders);

        List<RollBackEventModel> eventsList = new ArrayList<>();

        orderItemsList.forEach(item -> {
            eventsList.add(
                    new RollBackEventModel(
                            item.getProductId(),
                            item.getVendorId(),
                            item.getQuantity()
                    )
            );

        });

        if(!eventsList.isEmpty()) {
            sendInventoryRollBackEventUseCase.sendRollBack(new RollBackInventoryEvent(eventsList));
        }

        orders.forEach(orderEntity -> {
            Order order = OrderMappers.toOrder(orderEntity);
            putOrderCache(order);
        });

        System.out.println("OrderCheckScheduler finished with expire " + orders.size()+" orders !");

    }

    private void putOrderCache(Order order) {
        Cache cache = cacheManager.getCache("order");
        if(cache != null) {
            cache.put(order.getOrderNumber(),order);
        }
        Cache ordersCache = cacheManager.getCache("orders");
        if (ordersCache != null) {
            List<Order> userOrders = ordersCache.get(order.getUserId(), List.class);
            if (userOrders == null) {
                userOrders = new ArrayList<>();
            } else {
                userOrders = new ArrayList<>(userOrders);
            }
            userOrders.add(order);
            ordersCache.put(order.getUserId(), userOrders);
        }
    }


}
