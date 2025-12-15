package org.radon.shopyvendor.product.infrastructure.adapter.mapper;

import com.radon.grpc.ProductsResponse;
import org.radon.shopyvendor.shared.domain.model.Paged;
import org.radon.shopyvendor.product.domain.model.Product;
import org.radon.shopyvendor.product.infrastructure.repository.entity.ProductEntity;
import org.radon.shopyvendor.product.presentation.dto.ProductRequest;
import org.radon.shopyvendor.product.presentation.dto.ProductResponse;
import org.radon.shopyvendor.shared.dto.PagedResponse;

public class ProductMapper {

    public static ProductsResponse toGrpcProductsResponse(Paged<Product> page) {

        ProductsResponse.Builder builder = ProductsResponse.newBuilder();

        builder.setPageNumber(page.getPageNumber());
        builder.setPageSize(page.getPageSize());
        builder.setTotalPages(page.getTotalPages());
        builder.setTotalElements(page.getTotalElements());

        page.getContent().forEach(product -> builder.addProducts(toGrpcProductResponse(product)));

        return builder.build();

    }


    public static com.radon.grpc.ProductResponse toGrpcProductResponse(Product product) {
        return com.radon.grpc.ProductResponse.newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .setDetails(product.getDetails())
                .setDescription(product.getDescription())
                .setPrice(String.valueOf(product.getPrice()))
                .setQuantity(product.getQuantity())
                .build();
    }

    public static ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getDetails(),
                String.valueOf(product.getPrice()),
                product.getQuantity()
        );
    }

    public static PagedResponse<ProductResponse> toPagedResponse(Paged<Product> products) {
        return new PagedResponse<>(
                products.getContent().stream().map(ProductMapper::toProductResponse).toList(),
                products.getPageNumber(),
                products.getPageSize(),
                products.getTotalPages(),
                products.getTotalElements()
        );
    }

    public static Product toProductForAdd(ProductRequest productRequest) {
        return new Product(
                null,
                productRequest.getName(),
                productRequest.getDescription(),
                productRequest.getDetails(),
                Double.parseDouble(productRequest.getPrice()),
                productRequest.getQuantity()
        );
    }

    public static Product toProductForUpdate(ProductRequest productRequest,Long productId) {
        return new Product(
                productId,
                productRequest.getName(),
                productRequest.getDescription(),
                productRequest.getDetails(),
                Double.parseDouble(productRequest.getPrice()),
                productRequest.getQuantity()
        );
    }

    public static Product toProduct(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getDetails(),
                productEntity.getPrice(),
                String.valueOf(productEntity.getInventory().getQuantity())
        );
    }

    public static ProductEntity toProductEntity(Product product) {
        return new ProductEntity(
                product.getName(),
                product.getDescription(),
                product.getDetails(),
                product.getPrice(),
                null,
                null
        );
    }

}
