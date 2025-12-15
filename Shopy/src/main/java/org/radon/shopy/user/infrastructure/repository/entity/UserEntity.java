package org.radon.shopy.user.infrastructure.repository.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.radon.shopy.address.infrastructure.repository.entity.AddressEntity;

import java.util.List;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "user_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq"
    )
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(name = "phone_number",unique = true, nullable = false)
    private String phoneNumber;
    private String password;
    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<AddressEntity> addressEntities;
    private Byte age;


    public UserEntity(String fullName, String email, String phoneNumber, String password, Byte age) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.age = age;
    }

}
