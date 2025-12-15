package org.radon.shopyvendor.inventory.infrastructure.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.radon.shopyvendor.product.infrastructure.repository.entity.ProductEntity;
import org.radon.shopyvendor.vendor.infrastructure.repository.entity.VendorEntity;

import java.time.LocalDateTime;


@Entity
@Table(
        name = "inventory_table",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"product_id"})
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InventoryEntity {

    @Id
    @SequenceGenerator(
            name = "inv_seq",
            sequenceName = "inv_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inv_seq"
    )
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    private int quantity;
    private String createdAt = LocalDateTime.now().toString();
    private String updatedAt = LocalDateTime.now().toString();

}
