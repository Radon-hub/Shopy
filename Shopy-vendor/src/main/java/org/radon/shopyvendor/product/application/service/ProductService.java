package org.radon.shopyvendor.product.application.service;

import org.radon.shopyvendor.product.application.port.in.*;
import org.radon.shopyvendor.product.application.port.out.ProductRepository;
import org.radon.shopyvendor.shared.domain.model.Paged;
import org.radon.shopyvendor.product.domain.model.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements DeleteProductUseCase, UpdateProductUseCase, GetProductUseCase, GetProductsPaginationOfSpecificVendorUseCase {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @CachePut(value="product",key = "#product.id")
    @Override
    public Product updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    @CacheEvict(value = "product",key = "#id")
    @Override
    public String deleteProduct(Long id) {
        return productRepository.deleteProduct(id);
    }

    @Cacheable(value = "product",key = "#id")
    @Override
    public Product getProduct(Long id) {
        return productRepository.getProduct(id);
    }

    @Override
    public Paged<Product> getProductsPaginationOfSpecificVendorUseCase(Pageable pageable, Long vendorId) {
        return productRepository.getProductsPaginationOfSpecificVendorUseCase(pageable,vendorId);
    }
}
