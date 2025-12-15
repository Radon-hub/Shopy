package org.radon.shopyvendor.vendor.domain.model;

import org.radon.shopyvendor.product.domain.model.Product;
import org.radon.shopyvendor.shared.aop.exceptionHandling.VendorRegisterArgumentException;

import java.util.List;

public class Vendor {

    private Long id;
    private String name;
    private String description;
    private String phoneNumber;
    private String password;
    private Address address;
    private List<Product> productsList;

    public Vendor(Long id, String name, String description, String phoneNumber, String password, Address address, List<Product> productsList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.productsList = productsList;
    }

    public Vendor() {
    }

    public void isValidForRegister(){
        if(!isNameValid()) throw new VendorRegisterArgumentException("Name must be bigger than 5 characters!");
        if(!isPhoneNumberValid()) throw new VendorRegisterArgumentException("Phone number incorrect!");
        if(!isPasswordValid()) throw new VendorRegisterArgumentException("Password must be bigger than 5 characters!");
        isAddressValid();
    }


    private boolean isNameValid(){
        return name != null && name.length() > 5;
    }

    private boolean isPhoneNumberValid(){
        return phoneNumber != null && phoneNumber.length() == 11;
    }

    private boolean isPasswordValid(){
        return password != null && password.length() > 5;
    }

    private void isAddressValid() {
        if(address.getProvince().length() <= 3) throw new VendorRegisterArgumentException("Province is too short!");
        if(address.getCity().length() <= 3) throw new VendorRegisterArgumentException("City is too short!");
        if(address.getStreet().length() <= 3) throw new VendorRegisterArgumentException("Street is too short!");
        if(address.getNumber().isEmpty()) throw new VendorRegisterArgumentException("Address number is missing!");
        if(address.getArea().length() <= 3) throw new VendorRegisterArgumentException("Area is wrong!");
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }
}
