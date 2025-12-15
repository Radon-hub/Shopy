package org.radon.shopy.integration.vendor.application.port.in;

import org.radon.shopy.integration.vendor.domain.model.Product;
import org.radon.shopy.integration.vendor.presentation.dto.ProductResponseDto;

public interface GetProductUseCase {
    Product getProduct(Long id);
}
