package org.radon.shopy.integration.vendor.infrastructure.repository;


import com.radon.grpc.*;
import org.springframework.stereotype.Component;

@Component
public class VendorGrpcClient {

    private final VendorsGrpc.VendorsBlockingStub vendorBlockingStub;
    private final ProductsGrpc.ProductsBlockingStub productsBlockingStub;

    public VendorGrpcClient(VendorsGrpc.VendorsBlockingStub vendorBlockingStub, ProductsGrpc.ProductsBlockingStub productsBlockingStub) {
        this.vendorBlockingStub = vendorBlockingStub;
        this.productsBlockingStub = productsBlockingStub;
    }


    public VendorResponse getVendor(VendorRequest vendorRequest) {
        return vendorBlockingStub.getVendorById(vendorRequest);
    }

    public ProductResponse getProduct(ProductRequest productRequest) {
        return productsBlockingStub.getProduct(productRequest);
    }

    public ProductsResponse getProductsPagination(ProductsRequest productsRequest) {
        return productsBlockingStub.getVendorProducts(productsRequest);
    }



}