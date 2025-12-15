package org.radon.shopyorder.order.infrastructure.adapter;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.radon.shopyorder.cart.application.port.in.GetCartByUserIdUseCase;
import org.radon.shopyorder.cart.domain.model.Cart;
import org.radon.shopyorder.order.application.port.out.OrderRepository;
import org.radon.shopyorder.order.domain.model.Order;
import org.radon.shopyorder.order.domain.model.OrderItem;
import org.radon.shopyorder.order.domain.model.OrderStatus;
import org.radon.shopyorder.order.domain.model.Validators;
import org.radon.shopyorder.order.infrastructure.adapter.mapper.OrderMappers;
import org.radon.shopyorder.order.infrastructure.repository.OrderItemJpaRepository;
import org.radon.shopyorder.order.infrastructure.repository.OrderJpaRepository;
import org.radon.shopyorder.order.infrastructure.repository.entity.OrderEntity;
import org.radon.shopyorder.shared.aop.exceptionHandling.OrderNotFound;
import org.radon.shopyorder.shared.aop.exceptionHandling.SubmittedOrderConflict;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderItemJpaRepository orderItemJpaRepository;

    @Transactional
    @Override
    public Order checkout(String orderNumber) {
        Optional<OrderEntity> orderEntity = orderJpaRepository.findOrderEntitiesByOrderNumber(orderNumber);

        if(orderEntity.isEmpty()) {
            throw new OrderNotFound();
        }

        OrderEntity order = orderEntity.get();

        if(!order.getOrderStatus().equals(OrderStatus.CREATED)) {
            throw new IllegalStateException("Can not checkout this order ! Because Status is : " + order.getOrderStatus());
        }

        order.setOrderStatus(OrderStatus.PENDING_PAYMENT);
        order.setUpdatedAt(LocalDateTime.now());

        return OrderMappers.toOrder(orderEntity.get());

    }

    @Transactional
    @Override
    public Order submitOrder(Order order) {

        List<OrderEntity> userOrders = orderJpaRepository.findOrderEntitiesByUserIdAndOrderStatusIn(order.getUserId(),List.of(OrderStatus.CREATED,OrderStatus.PROCESSING,OrderStatus.PENDING_PAYMENT));

        for (OrderEntity orderEntity : userOrders) {
            boolean match = Validators.orderItemsMatchCartItems(order.getItems(),orderEntity.getItems());
            if(match) throw new SubmittedOrderConflict();
        }

        OrderEntity orderEntity = OrderMappers.toOrderEntity(order);

        OrderEntity savedOrder = orderJpaRepository.save(orderEntity);

        val listOfItems = order.getItems()
                .stream()
                .map(item -> OrderMappers.toOrderItemEntity(item,savedOrder))
                .toList();

        orderItemJpaRepository.saveAll(listOfItems);

        savedOrder.setItems(listOfItems);

        return OrderMappers.toOrder(savedOrder);
    }

    @Transactional
    @Override
    public Order cancelOrder(Order order) {
        Optional<OrderEntity> orderEntityPersistence = orderJpaRepository.findOrderEntitiesByOrderNumberAndUserIdAndOrderStatusIn(order.getOrderNumber(),order.getUserId(),List.of(OrderStatus.CREATED,OrderStatus.PROCESSING,OrderStatus.PENDING_PAYMENT));

        if(orderEntityPersistence.isEmpty()) {
            throw new OrderNotFound();
        }

        OrderEntity orderEntity = orderEntityPersistence.get();

        orderEntity.setOrderStatus(OrderStatus.CANCELLED);
        orderEntity.setUpdatedAt(LocalDateTime.now());

        return OrderMappers.toOrder(orderEntity);
    }

    @Override
    public Order getOrderByNumber(String orderNumber) {

        Optional<OrderEntity> orderEntity = orderJpaRepository.findOrderEntitiesByOrderNumber(orderNumber);

        if(orderEntity.isEmpty()) {
            throw new OrderNotFound();
        }

        return OrderMappers.toOrder(orderEntity.get());
    }

    @Override
    public List<Order> getUserOrders(Long userID,List<OrderStatus> orderStatusList) {

        List<OrderEntity> orderEntities = (orderStatusList == null || orderStatusList.isEmpty())
            ? orderJpaRepository.findOrderEntitiesByUserId(userID)
            : orderJpaRepository.findOrderEntitiesByUserIdAndOrderStatusIn(userID,orderStatusList);


        if(orderEntities.isEmpty()) {
            throw new OrderNotFound();
        }

        return orderEntities.stream().map(OrderMappers::toOrder).toList();
    }


}
