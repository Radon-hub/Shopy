package org.radon.shopy.integration.vendor.domain.model;

public class Product {
    private Long id;
    private String name;
    private String description;
    private String details;
    private String price;
    private String quantity;

    public Product(){}

    public Product(Long id, String name, String description, String details, String price, String quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.details = details;
        this.price = price;
        this.quantity = quantity;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
