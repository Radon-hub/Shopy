package org.radon.shopyvendor.inventory.domain.model;

import org.radon.shopyvendor.product.domain.model.Product;
import org.radon.shopyvendor.vendor.domain.model.Vendor;

public class Inventory {

    private Long id;
    private Product product;
    private int quantity;
    private String createdAt;
    private String updatedAt;

    public Inventory(Long id, Product product, int quantity, String createdAt, String updatedAt) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Inventory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
