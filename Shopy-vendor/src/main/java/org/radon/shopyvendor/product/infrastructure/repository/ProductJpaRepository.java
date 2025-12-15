package org.radon.shopyvendor.product.infrastructure.repository;

import org.radon.shopyvendor.product.infrastructure.repository.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    Page<ProductEntity> findByVendorEntity_Id(Pageable pageable,Long vendorId);
    Optional<ProductEntity> findProductEntityById(Long id);
}
