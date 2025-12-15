package org.radon.shopyvendor.vendor.infrastructure.adapter;

import jakarta.transaction.Transactional;
import org.radon.shopyvendor.shared.aop.exceptionHandling.VendorExistsException;
import org.radon.shopyvendor.shared.aop.exceptionHandling.VendorNotFoundException;
import org.radon.shopyvendor.vendor.application.port.out.VendorRepository;
import org.radon.shopyvendor.vendor.domain.model.Vendor;
import org.radon.shopyvendor.vendor.infrastructure.adapter.mapper.VendorMappers;
import org.radon.shopyvendor.vendor.infrastructure.repository.VendorJpaRepository;
import org.radon.shopyvendor.vendor.infrastructure.repository.entity.VendorEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class VendorRepositoryImpl implements VendorRepository {

    private final VendorJpaRepository vendorJpaRepository;

    public VendorRepositoryImpl(VendorJpaRepository vendorJpaRepository) {
        this.vendorJpaRepository = vendorJpaRepository;
    }

    @Override
    public String deleteVendor(Vendor vendor) {

        Optional<VendorEntity> vendorEntity = vendorJpaRepository.findById(vendor.getId());

        if(vendorEntity.isEmpty()){
            throw new VendorExistsException();
        }

        vendorJpaRepository.delete(vendorEntity.get());

        return "Vendor deleted successfully!";
    }

    @Override
    @Transactional
    public Vendor editVendor(Vendor vendor) {
        Optional<VendorEntity> vendorEntity = vendorJpaRepository.findById(vendor.getId());

        if(vendorEntity.isEmpty()){
            throw new VendorNotFoundException(vendor.getId().toString());
        }

        VendorEntity vendorEntityToEdit = vendorEntity.get();

        vendorEntityToEdit.setName(vendor.getName());
        vendorEntityToEdit.setPhoneNumber(vendor.getPhoneNumber());
        vendorEntityToEdit.setDescription(vendor.getDescription());
        vendorEntityToEdit.setAddress(VendorMappers.toAddressEntity(vendor.getAddress()));

        return VendorMappers.toVendor(vendorEntityToEdit);
    }

    @Override
    public Vendor getVendor(Long id) {
        Optional<VendorEntity> vendorEntity = vendorJpaRepository.findById(id);

        if(vendorEntity.isEmpty()){
            throw new VendorNotFoundException(id.toString());
        }

        return VendorMappers.toVendor(vendorEntity.get());
    }


}
