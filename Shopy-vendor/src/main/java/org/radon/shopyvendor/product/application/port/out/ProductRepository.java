package org.radon.shopyvendor.product.application.port.out;

import org.radon.shopyvendor.shared.domain.model.Paged;
import org.radon.shopyvendor.product.domain.model.Product;
import org.radon.shopyvendor.vendor.domain.model.Vendor;
import org.springframework.data.domain.Pageable;

public interface ProductRepository {
    String deleteProduct(Long id);
    Product getProduct(Long id);
    Product updateProduct(Product product);
    Product addNewProduct(Product product);
    Paged<Product> getProductsPaginationOfSpecificVendorUseCase(Pageable pageable, Long vendorId);
}
