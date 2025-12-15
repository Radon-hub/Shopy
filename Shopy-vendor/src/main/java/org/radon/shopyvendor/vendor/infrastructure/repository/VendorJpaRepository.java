package org.radon.shopyvendor.vendor.infrastructure.repository;

import org.radon.shopyvendor.vendor.infrastructure.repository.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorJpaRepository extends JpaRepository<VendorEntity, Long> {
    Optional<VendorEntity> findVendorEntitiesByPhoneNumber(String phoneNumber);

    VendorEntity getVendorEntitiesByPhoneNumber(String phoneNumber);
}
