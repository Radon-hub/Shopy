package org.radon.shopyorder.events.domain.model;

import java.io.Serializable;

public class UserRegisteredEvent implements Serializable {

    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String createdAt;

    public UserRegisteredEvent() {}

    public UserRegisteredEvent(Long userId, String name, String email, String phone, String createdAt) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
