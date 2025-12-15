package org.radon.shopyvendor.product.application.port.in;

import org.radon.shopyvendor.shared.domain.model.Paged;
import org.radon.shopyvendor.product.domain.model.Product;
import org.springframework.data.domain.Pageable;

public interface GetProductsPaginationOfSpecificVendorUseCase {
    Paged<Product> getProductsPaginationOfSpecificVendorUseCase(Pageable pageable, Long vendorId);
}
