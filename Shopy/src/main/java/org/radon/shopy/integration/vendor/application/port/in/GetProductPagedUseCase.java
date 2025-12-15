package org.radon.shopy.integration.vendor.application.port.in;

import org.radon.shopy.integration.vendor.domain.model.Product;
import org.radon.shopy.integration.vendor.presentation.dto.ProductResponseDto;
import org.radon.shopy.shared.dto.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetProductPagedUseCase {
    PagedResponse<Product> getProductsPaged(Pageable pageable, Long vendorId);
}
