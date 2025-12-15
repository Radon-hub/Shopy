package org.radon.shopy.integration.vendor.application.port.out;


import org.radon.shopy.integration.vendor.domain.model.Product;
import org.radon.shopy.integration.vendor.domain.model.Vendor;
import org.radon.shopy.integration.vendor.presentation.dto.ProductResponseDto;
import org.radon.shopy.integration.vendor.presentation.dto.VendorResponseDto;
import org.radon.shopy.shared.dto.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VendorClientRepository {
    PagedResponse<Product> getProductsPaged(Pageable pageable, Long vendorId);
    Product getProduct(Long id);
    Vendor getVendor(Long id);
}
