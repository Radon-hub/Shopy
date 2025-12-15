package org.radon.shopyvendor.product.application.port.in;

import org.radon.shopyvendor.product.domain.model.Product;

public interface UpdateProductUseCase {
    Product updateProduct(Product product);
}
