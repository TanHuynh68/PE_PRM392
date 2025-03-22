package com.example.pe_tan_dep_trai.model;

import java.io.Serializable;

public class Author implements Serializable {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Author(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getAuthorId() {
        return id;
    }

    public void setAuthorId(String authorId) {
        this.id = authorId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
