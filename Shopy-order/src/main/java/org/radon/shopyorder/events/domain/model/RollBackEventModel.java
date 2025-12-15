package org.radon.shopyorder.events.domain.model;

public class RollBackEventModel {
    private Long productId;
    private Long vendorId;
    private int quantity;

    public RollBackEventModel(Long productId, Long vendorId, int quantity) {
        this.productId = productId;
        this.vendorId = vendorId;
        this.quantity = quantity;
    }

    public RollBackEventModel() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
