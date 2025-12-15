package org.radon.shopyvendor.product.application.port.in;

import org.radon.shopyvendor.product.domain.model.Product;
import org.radon.shopyvendor.product.infrastructure.repository.entity.ProductEntity;
import org.radon.shopyvendor.vendor.domain.model.Vendor;

public interface AddNewProductUseCase {
    Product addNewProduct(Product product);
}
