package com.myapp.fieldsbs;

public class User {
    String name;
    String email;
    String pwd;
    String id;
    String phone;

    public User() {

    }

    public User(String name, String email, String pwd, String id) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public String getId() {
        return id;
    }

}
