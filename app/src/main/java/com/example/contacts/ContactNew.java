package com.example.contacts;

public class ContactNew {
    public String id;
    public String name;
    public String phone;
    public String email;
    public String isFavorite;

    public ContactNew(String id, String name, String phone, String email, String isFavorite) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.isFavorite = isFavorite;
    }

    public ContactNew(String name, String phone, String email) {
        this.id = "0";
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.isFavorite = "0";
    }
}