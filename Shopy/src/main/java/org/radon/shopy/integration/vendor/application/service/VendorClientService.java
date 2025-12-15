package org.radon.shopy.integration.vendor.application.service;

import org.radon.shopy.integration.vendor.application.port.in.GetProductPagedUseCase;
import org.radon.shopy.integration.vendor.application.port.in.GetProductUseCase;
import org.radon.shopy.integration.vendor.application.port.in.GetVendorUseCase;
import org.radon.shopy.integration.vendor.application.port.out.VendorClientRepository;
import org.radon.shopy.integration.vendor.domain.model.Product;
import org.radon.shopy.integration.vendor.domain.model.Vendor;
import org.radon.shopy.integration.vendor.presentation.dto.ProductResponseDto;
import org.radon.shopy.integration.vendor.presentation.dto.VendorResponseDto;
import org.radon.shopy.shared.dto.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VendorClientService implements GetVendorUseCase, GetProductUseCase, GetProductPagedUseCase {

    private final VendorClientRepository vendorClientRepository;

    public VendorClientService(VendorClientRepository vendorClientRepository) {
        this.vendorClientRepository = vendorClientRepository;
    }


    @Override
    public Vendor getVendor(Long id) {
        return vendorClientRepository.getVendor(id);
    }

    @Override
    public PagedResponse<Product> getProductsPaged(Pageable pageable, Long vendorId) {
        return vendorClientRepository.getProductsPaged(pageable, vendorId);
    }

    @Override
    public Product getProduct(Long id) {
        return vendorClientRepository.getProduct(id);
    }
}
