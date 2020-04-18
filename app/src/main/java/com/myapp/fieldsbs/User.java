package com.myapp.fieldsbs;

public class User {
    String name;
    String email;
    String phone;
    String isAdmin;

    public User() {

    }

    public User(String name, String email, String phone, String isAdmin) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getIsAdmin() { return isAdmin; }

    public String getPhone() { return phone; }

}
