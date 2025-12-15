package org.radon.shopyvendor.product.infrastructure.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.radon.shopyvendor.inventory.infrastructure.repository.InventoryEntity;
import org.radon.shopyvendor.vendor.infrastructure.repository.entity.VendorEntity;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "product_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @SequenceGenerator(
            name = "product_seq",
            sequenceName = "product_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_seq"
    )
    private Long id;
    private String name;
    private String description;
    private String details;
    private double price;
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private VendorEntity vendorEntity;
    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private InventoryEntity inventory;

    public ProductEntity(String name, String description, String details, double price, InventoryEntity inventory,VendorEntity vendorEntity) {
        this.name = name;
        this.description = description;
        this.details = details;
        this.price = price;
        this.inventory = inventory;
    }

}
