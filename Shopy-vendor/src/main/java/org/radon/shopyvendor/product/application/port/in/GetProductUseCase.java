package org.radon.shopyvendor.product.application.port.in;

import org.radon.shopyvendor.product.domain.model.Product;

public interface GetProductUseCase {
    Product getProduct(Long id);
}
