package org.radon.shopy.address.infrastructure.repository.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.radon.shopy.user.infrastructure.repository.entity.UserEntity;

@Entity
@Table(name = "address_table")
@Setter
@Getter
@NoArgsConstructor
public class AddressEntity {

    @Id
    @SequenceGenerator(
            name = "address_seq",
            sequenceName = "address_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_seq"
    )
    private Long id;
    private String title;
    private String province;
    private String city;
    private String area;
    private String street;
    @Column(name = "extra_details")
    private String extraDetails;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public AddressEntity(String title, String province, String city, String area, String street, String extraDetails, UserEntity userEntity) {
        this.title = title;
        this.province = province;
        this.city = city;
        this.area = area;
        this.street = street;
        this.extraDetails = extraDetails;
        this.userEntity = userEntity;
    }
}
