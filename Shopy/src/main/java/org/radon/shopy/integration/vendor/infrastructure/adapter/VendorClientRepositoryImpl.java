package org.radon.shopy.integration.vendor.infrastructure.adapter;

import com.radon.grpc.ProductRequest;
import com.radon.grpc.ProductsRequest;
import com.radon.grpc.VendorRequest;
import org.radon.shopy.integration.vendor.application.port.out.VendorClientRepository;
import org.radon.shopy.integration.vendor.domain.model.Product;
import org.radon.shopy.integration.vendor.domain.model.Vendor;
import org.radon.shopy.integration.vendor.infrastructure.adapter.mapper.GRPCMapper;
import org.radon.shopy.integration.vendor.infrastructure.repository.VendorGrpcClient;
import org.radon.shopy.integration.vendor.presentation.dto.ProductResponseDto;
import org.radon.shopy.integration.vendor.presentation.dto.VendorResponseDto;
import org.radon.shopy.shared.dto.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class VendorClientRepositoryImpl implements VendorClientRepository {

    private final VendorGrpcClient vendorGrpcClient;

    public VendorClientRepositoryImpl(VendorGrpcClient vendorGrpcClient) {
        this.vendorGrpcClient = vendorGrpcClient;
    }


    @Override
    public PagedResponse<Product> getProductsPaged(Pageable pageable, Long vendorId) {
        return GRPCMapper.toPagedProducts(
                vendorGrpcClient.getProductsPagination(
                        ProductsRequest.newBuilder()
                                .setVendorId(vendorId)
                                .setPage(pageable.getPageNumber())
                                .setSize(pageable.getPageSize())
                                .build())
        );
    }

    @Override
    public Product getProduct(Long id) {
        return GRPCMapper.toProduct(
                vendorGrpcClient.getProduct(ProductRequest.newBuilder().setId(id).build())
        );
    }

    @Override
    public Vendor getVendor(Long id) {
        return GRPCMapper.toVendor(
                vendorGrpcClient.getVendor(
                        VendorRequest.newBuilder().setId(id).build()
                )
        );
    }
}
