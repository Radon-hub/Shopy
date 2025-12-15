package org.radon.shopy.shared.configuration;

import com.radon.grpc.InventoryGrpc;
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
    public ManagedChannel vendorChannel() {
        return NettyChannelBuilder
                .forAddress("shopy-vendor-app", 2020)
                .usePlaintext()
                .build();
    }


    @Bean
    public InventoryGrpc.InventoryBlockingStub inventoryStub(ManagedChannel vendorChannel) {
        return InventoryGrpc.newBlockingStub(vendorChannel);
    }
    @Bean
    public OrdersGrpc.OrdersBlockingStub orderStub(ManagedChannel orderChannel) {
        return OrdersGrpc.newBlockingStub(orderChannel);
    }

    @Bean
    public CartsGrpc.CartsBlockingStub cartStub(ManagedChannel orderChannel) {
        return CartsGrpc.newBlockingStub(orderChannel);
    }

    @Bean
    public VendorsGrpc.VendorsBlockingStub vendorStub(ManagedChannel vendorChannel) {
        return VendorsGrpc.newBlockingStub(vendorChannel);
    }

    @Bean
    public ProductsGrpc.ProductsBlockingStub productsStub(ManagedChannel vendorChannel) {
        return ProductsGrpc.newBlockingStub(vendorChannel);
    }

}