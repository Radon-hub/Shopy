package org.radon.shopy.address.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.radon.shopy.user.domain.model.User;
import org.radon.shopy.user.infrastructure.repository.entity.UserEntity;

import java.io.Serializable;

public class Address implements Serializable {

    private Long id;
    private String title;
    private String province;
    private String city;
    private String area;
    private String street;
    private String extraDetails;
    private User user;

    public Address() {}

    public Address(String title, String province, String city, String area, String street, String extraDetails,User user) {
        this.title = title;
        this.province = province;
        this.city = city;
        this.area = area;
        this.street = street;
        this.extraDetails = extraDetails;
        this.user = user;
    }


    public User getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getStreet() {
        return street;
    }

    public String getExtraDetails() {
        return extraDetails;
    }
}
