package org.radon.shopy.integration.vendor.infrastructure.adapter.mapper;

import com.radon.grpc.AddressResponse;
import com.radon.grpc.ProductResponse;
import com.radon.grpc.ProductsResponse;
import com.radon.grpc.VendorResponse;
import org.radon.shopy.integration.vendor.domain.model.Address;
import org.radon.shopy.integration.vendor.domain.model.Product;
import org.radon.shopy.integration.vendor.domain.model.Vendor;
import org.radon.shopy.integration.vendor.presentation.dto.ProductResponseDto;
import org.radon.shopy.integration.vendor.presentation.dto.VendorAddressResponseDto;
import org.radon.shopy.integration.vendor.presentation.dto.VendorResponseDto;
import org.radon.shopy.shared.dto.PagedResponse;

public class GRPCMapper {


    public static PagedResponse<Product> toPagedProducts(ProductsResponse productsResponse){
        return new PagedResponse<>(
                productsResponse.getProductsList().stream().map(GRPCMapper::toProduct).toList(),
                productsResponse.getPageNumber(),
                productsResponse.getPageSize(),
                productsResponse.getTotalPages(),
                productsResponse.getTotalElements()
        );
    }

    public static PagedResponse<ProductResponseDto> toPagedProductsDto(PagedResponse<Product> products){
        return new PagedResponse<>(
                products.getContent().stream().map(GRPCMapper::toProductResponseDto).toList(),
                products.getPageNumber(),
                products.getPageSize(),
                products.getTotalPages(),
                products.getTotalElements()
        );
    }

    public static Product toProduct(ProductResponse productResponse) {
        return new Product(
                productResponse.getId(),
                productResponse.getName(),
                productResponse.getDescription(),
                productResponse.getDetails(),
                productResponse.getPrice(),
                productResponse.getQuantity()
        );
    }


    public static ProductResponseDto toProductResponseDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getDetails(),
                product.getPrice(),
                product.getQuantity()
        );
    }


    public static ProductResponseDto toProductResponseDto(ProductResponse productResponse) {
        return new ProductResponseDto(
                productResponse.getId(),
                productResponse.getName(),
                productResponse.getDescription(),
                productResponse.getDetails(),
                productResponse.getPrice(),
                productResponse.getQuantity()
        );
    }

    public static Vendor toVendor(VendorResponse vendorResponse) {
        return new Vendor(
                vendorResponse.getId(),
                vendorResponse.getName(),
                vendorResponse.getDescription(),
                vendorResponse.getPhoneNumber(),
                toVendorAddress(vendorResponse.getAddress()),
                null
        );
    }

    public static VendorResponseDto toVendorResponseDto(Vendor vendorResponse) {
        return new VendorResponseDto(
                vendorResponse.getId(),
                vendorResponse.getName(),
                vendorResponse.getDescription(),
                vendorResponse.getPhoneNumber(),
                toVendorAddressResponseDto(vendorResponse.getAddress())
        );
    }


    public static Address toVendorAddress(AddressResponse addressResponse) {
        return new Address(
                addressResponse.getTitle(),
                addressResponse.getProvince(),
                addressResponse.getCity(),
                addressResponse.getArea(),
                addressResponse.getStreet(),
                addressResponse.getNumber()
        );
    }

    public static VendorAddressResponseDto toVendorAddressResponseDto(Address addressResponse) {
        return new VendorAddressResponseDto(
                addressResponse.getTitle(),
                addressResponse.getProvince(),
                addressResponse.getCity(),
                addressResponse.getArea(),
                addressResponse.getStreet(),
                addressResponse.getNumber()
        );
    }
}
