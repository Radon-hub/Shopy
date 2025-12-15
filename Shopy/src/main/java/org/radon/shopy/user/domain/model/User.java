package org.radon.shopy.user.domain.model;

import org.radon.shopy.address.domain.model.Address;
import org.radon.shopy.address.infrastructure.repository.entity.AddressEntity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private Long id;
    private FullName fullName;
    private Email email;
    private PhoneNumber phoneNumber;
    private Password password;
    private List<Address> addressList;
    private Age age;

    public User(){}

    public User(Long id, FullName fullName, Email email, PhoneNumber phoneNumber, Password password, List<Address> addressList, Age age) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.addressList = addressList;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }
}
