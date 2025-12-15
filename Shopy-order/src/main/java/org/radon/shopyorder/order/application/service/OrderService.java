package org.radon.shopyorder.order.application.service;

import jakarta.transaction.Transactional;
import org.radon.shopyorder.cart.application.port.in.GetCartByUserIdUseCase;
import org.radon.shopyorder.cart.domain.model.Cart;
import org.radon.shopyorder.cart.domain.model.CartStatus;
import org.radon.shopyorder.order.application.port.in.*;
import org.radon.shopyorder.order.application.port.out.OrderRepository;
import org.radon.shopyorder.order.domain.model.Order;
import org.radon.shopyorder.order.domain.model.OrderStatus;
import org.radon.shopyorder.shared.aop.exceptionHandling.CartIsAbandoned;
import org.radon.shopyorder.shared.aop.exceptionHandling.CartIsEmpty;
import org.radon.shopyorder.shared.aop.exceptionHandling.CartNotBelongToUser;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class OrderService implements GetUserOrdersUseCase,SubmitOrderUseCase, CheckOutUseCase, CancelOrderUseCase , GetOrderByNumberUseCase {

    private final OrderRepository orderRepository;
    private final GetCartByUserIdUseCase getCartByUserIdUseCase;

    public OrderService(OrderRepository orderRepository, GetCartByUserIdUseCase getCartByUserIdUseCase) {
        this.orderRepository = orderRepository;
        this.getCartByUserIdUseCase = getCartByUserIdUseCase;
    }

    @CachePut(value = "order",key = "#orderNumber",unless = "#result == null")
    @Override
    public Order checkout(String orderNumber) {
        return orderRepository.checkout(orderNumber);
    }

    @Transactional
    @CachePut(value = "order",key = "#result.orderNumber",unless = "#result == null")
    @Override
    public Order submitOrder(Long userID, UUID cartID) {

        Cart cart = getCartByUserIdUseCase.getCartByUserId(userID);

        if(!cart.getUserId().equals(userID) || !cart.getId().equals(cartID)){
            throw new CartNotBelongToUser(cartID.toString(),userID.toString());
        }

        if(cart.getCartStatus() == CartStatus.ABANDONED){
            throw new CartIsAbandoned(cartID.toString());
        }

        if(cart.getItems().isEmpty()){
            throw new CartIsEmpty();
        }

        Order newOrder = new Order(userID,cart.getItems());

        return orderRepository.submitOrder(newOrder);

    }

    @CachePut(value = "order",key = "#result.orderNumber",unless = "#result == null")
    @Override
    public Order cancelOrder(Long userId,String orderNumber) {
        return orderRepository.cancelOrder(
                new Order(null,orderNumber,userId,null,null,null,null,null,null,null)
        );
    }

    @Cacheable(value = "order",key = "#orderNumber",unless = "#result == null")
    @Override
    public Order getOrderByNumberUseCase(String orderNumber) {
        return  orderRepository.getOrderByNumber(orderNumber);
    }

    @Override
    public List<Order> getUserOrders(Long userID,List<OrderStatus> orderStatusList) {
        return orderRepository.getUserOrders(userID,orderStatusList);
    }


}
