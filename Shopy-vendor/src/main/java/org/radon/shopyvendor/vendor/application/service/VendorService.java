package org.radon.shopyvendor.vendor.application.service;

import org.radon.shopyvendor.vendor.application.port.in.*;
import org.radon.shopyvendor.vendor.application.port.out.VendorRepository;
import org.radon.shopyvendor.vendor.domain.model.Vendor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class VendorService implements DeleteVendorUseCase, EditVendorUseCase, GetVendorUseCase {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @CacheEvict(value = "vendor",key = "#vendor.id")
    @Override
    public String deleteCall(Vendor vendor) {
        return vendorRepository.deleteVendor(vendor);
    }

    @CachePut(value = "vendor",key = "#vendor.id")
    @Override
    public Vendor editCall(Vendor vendor) {
        return vendorRepository.editVendor(vendor);
    }

    @Cacheable(value = "vendor",key = "#id")
    @Override
    public Vendor getVendorCall(Long id) {
        return vendorRepository.getVendor(id);
    }
}
