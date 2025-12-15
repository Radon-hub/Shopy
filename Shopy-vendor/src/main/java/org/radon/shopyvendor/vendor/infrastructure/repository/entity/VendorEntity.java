package org.radon.shopyvendor.vendor.infrastructure.repository.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.radon.shopyvendor.product.infrastructure.repository.entity.ProductEntity;

import java.util.List;

@Entity
@Table(name = "vendor_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorEntity {

    @Id
    @SequenceGenerator(
            name = "vendor_seq",
            sequenceName = "vendor_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vendor_seq"
    )
    private Long id;
    private String name;
    private String description;
    private String phoneNumber;
    private String password;
    @Embedded
    private AddressEntity address;
    @OneToMany(mappedBy = "vendorEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductEntity> productsList;

    public VendorEntity(Long id) {
        this.id = id;
    }

    public VendorEntity(String name, String description, String phoneNumber, String password, AddressEntity address) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
    }
}
