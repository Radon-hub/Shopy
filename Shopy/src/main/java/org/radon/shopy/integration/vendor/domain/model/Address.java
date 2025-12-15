package org.radon.shopy.integration.vendor.domain.model;

public class Address {
    private String title;
    private String province;
    private String city;
    private String area;
    private String street;
    private String number;

    public Address(String title, String province, String city, String area, String street, String number) {
        this.title = title;
        this.province = province;
        this.city = city;
        this.area = area;
        this.street = street;
        this.number = number;
    }

    public Address() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
