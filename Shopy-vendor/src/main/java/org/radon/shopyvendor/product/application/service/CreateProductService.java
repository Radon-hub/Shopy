package org.radon.shopyvendor.product.application.service;

import jakarta.transaction.Transactional;
import lombok.val;
import org.radon.shopyvendor.product.application.port.in.AddNewProductUseCase;
import org.radon.shopyvendor.product.application.port.out.ProductRepository;
import org.radon.shopyvendor.product.domain.model.Product;
import org.radon.shopyvendor.vendor.application.port.out.VendorRepository;
import org.radon.shopyvendor.vendor.domain.model.Vendor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService implements AddNewProductUseCase {

    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @CachePut(value = "product",key = "#result.id")
    @Override
    public Product addNewProduct(Product product) {
        return productRepository.addNewProduct(product);
    }
}
