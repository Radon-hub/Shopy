package org.radon.shopyvendor.product.presentation.controller;

import com.radon.grpc.*;
import io.grpc.stub.StreamObserver;
import lombok.val;
import org.radon.shopyvendor.product.application.port.in.GetProductUseCase;
import org.radon.shopyvendor.product.application.port.in.GetProductsPaginationOfSpecificVendorUseCase;
import org.radon.shopyvendor.product.infrastructure.adapter.mapper.ProductMapper;
import org.radon.shopyvendor.shared.domain.model.Paged;
import org.radon.shopyvendor.product.domain.model.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.grpc.server.service.GrpcService;


@GrpcService
public class ProductsGrpcService extends ProductsGrpc.ProductsImplBase {


    private final GetProductUseCase getProductUseCase;
    private final GetProductsPaginationOfSpecificVendorUseCase getProductsPaginationOfSpecificVendorUseCase;

    public ProductsGrpcService(GetProductUseCase getProductUseCase, GetProductsPaginationOfSpecificVendorUseCase getProductsPaginationOfSpecificVendorUseCase) {
        this.getProductUseCase = getProductUseCase;
        this.getProductsPaginationOfSpecificVendorUseCase = getProductsPaginationOfSpecificVendorUseCase;
    }


    @Override
    public void getVendorProducts(ProductsRequest request, StreamObserver<ProductsResponse> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        val products= getProductsPaginationOfSpecificVendorUseCase.getProductsPaginationOfSpecificVendorUseCase(pageable,request.getVendorId());
        responseObserver.onNext(ProductMapper.toGrpcProductsResponse(products));
        responseObserver.onCompleted();
    }

    @Override
    public void getProduct(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {

        Product product = getProductUseCase.getProduct(request.getId());

        responseObserver.onNext(ProductMapper.toGrpcProductResponse(product));
        responseObserver.onCompleted();

    }




}
