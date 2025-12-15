package org.radon.shopyvendor.product.infrastructure.adapter;

import jakarta.transaction.Transactional;
import org.radon.shopyvendor.auth.application.port.in.GetCurrentVendorUseCase;
import org.radon.shopyvendor.inventory.infrastructure.repository.InventoryEntity;
import org.radon.shopyvendor.inventory.infrastructure.repository.InventoryJpaRepository;
import org.radon.shopyvendor.product.application.port.out.ProductRepository;
import org.radon.shopyvendor.shared.domain.model.Paged;
import org.radon.shopyvendor.product.domain.model.Product;
import org.radon.shopyvendor.product.infrastructure.adapter.mapper.ProductMapper;
import org.radon.shopyvendor.product.infrastructure.repository.ProductJpaRepository;
import org.radon.shopyvendor.product.infrastructure.repository.entity.ProductEntity;
import org.radon.shopyvendor.shared.aop.exceptionHandling.EmptyPaginationException;
import org.radon.shopyvendor.shared.aop.exceptionHandling.NoProductFoundException;
import org.radon.shopyvendor.shared.aop.exceptionHandling.ProductNotFoundException;
import org.radon.shopyvendor.vendor.domain.model.Vendor;
import org.radon.shopyvendor.vendor.infrastructure.adapter.mapper.VendorMappers;
import org.radon.shopyvendor.vendor.infrastructure.repository.VendorJpaRepository;
import org.radon.shopyvendor.vendor.infrastructure.repository.entity.VendorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final VendorJpaRepository vendorJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final GetCurrentVendorUseCase getCurrentVendorUseCase;


    public ProductRepositoryImpl(VendorJpaRepository vendorJpaRepository, ProductJpaRepository productJpaRepository, GetCurrentVendorUseCase getCurrentVendorUseCase) {
        this.vendorJpaRepository = vendorJpaRepository;
        this.productJpaRepository = productJpaRepository;
        this.getCurrentVendorUseCase = getCurrentVendorUseCase;
    }


    @Override
    public String deleteProduct(Long id) {

        Optional<ProductEntity> productEntity = productJpaRepository.findById(id);

        if(productEntity.isEmpty()){
            throw new ProductNotFoundException(id.toString());
        }

        productJpaRepository.deleteById(id);

        return "Product Successfully Deleted!";
    }

    @Override
    public Product getProduct(Long id) {
        Optional<ProductEntity> productEntity = productJpaRepository.findById(id);

        if(productEntity.isEmpty()){
            throw new ProductNotFoundException(id.toString());
        }

        return ProductMapper.toProduct(productEntity.get());
    }

    @Override
    @Transactional
    public Product updateProduct(Product product) {

        Optional<ProductEntity> productEntity = productJpaRepository.findById(product.getId());

        if(productEntity.isEmpty()){
            throw new ProductNotFoundException(product.getId().toString());
        }

        ProductEntity updatedProductEntity = productEntity.get();


        updatedProductEntity.setDescription(product.getDescription());
        updatedProductEntity.setName(product.getName());
        updatedProductEntity.setPrice(product.getPrice());
        updatedProductEntity.setDetails(product.getDetails());

        updatedProductEntity.getInventory().setQuantity(Integer.parseInt(product.getQuantity()));

        updatedProductEntity.getInventory().setUpdatedAt(LocalDateTime.now().toString());

        return ProductMapper.toProduct(updatedProductEntity);
    }

    @Transactional
    @Override
    public Product addNewProduct(Product product) {

        ProductEntity productEntity = ProductMapper.toProductEntity(product);

        String vendorUserName = getCurrentVendorUseCase.getCurrentVendor();
        VendorEntity vendor = vendorJpaRepository.getVendorEntitiesByPhoneNumber(vendorUserName);

        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setQuantity(Integer.parseInt(product.getQuantity()));
        inventoryEntity.setProduct(productEntity);

        productEntity.setVendorEntity(vendor);
        productEntity.setInventory(inventoryEntity);

        productJpaRepository.save(productEntity);

        return ProductMapper.toProduct(productEntity);
    }

    @Override
    public Paged<Product> getProductsPaginationOfSpecificVendorUseCase(Pageable pageable, Long vendorId) {
        Page<ProductEntity> prods = productJpaRepository.findByVendorEntity_Id(pageable,vendorId);

        if(prods.isEmpty()){
            throw new EmptyPaginationException(vendorId.toString(),String.valueOf(pageable.getPageNumber()));
        }

        if(prods.getTotalElements() == 0){
            throw new NoProductFoundException(vendorId.toString());
        }

        return new Paged<>(
                prods.getContent().stream().map(ProductMapper::toProduct).toList(),
                prods.getNumber(),
                prods.getSize(),
                prods.getTotalPages(),
                prods.getTotalElements()
        );
    }
}
