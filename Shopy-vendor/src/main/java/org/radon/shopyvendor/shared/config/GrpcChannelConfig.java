package org.radon.shopyvendor.shared.config;

import com.radon.grpc.ProductsGrpc;
import com.radon.grpc.VendorsGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;
import org.radon.grpc.CartsGrpc;
import org.radon.grpc.OrdersGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcChannelConfig {

    @Bean
    public ManagedChannel orderChannel() {
        return NettyChannelBuilder
                .forAddress("shopy-order-app", 2030)
                .usePlaintext()
                .build();
    }


    @Bean
    public OrdersGrpc.OrdersBlockingStub orderStub(ManagedChannel orderChannel) {
        return OrdersGrpc.newBlockingStub(orderChannel);
    }

    @Bean
    public CartsGrpc.CartsBlockingStub cartStub(ManagedChannel orderChannel) {
        return CartsGrpc.newBlockingStub(orderChannel);
    }

}