package com.myapp.fieldsbs;

public class User {
    private String name;
    private String email;
    private String id;
    private String phone;
    private String isAdmin;

    User(String name, String email, String id, String phone, String isAdmin) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.phone = phone;
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public String getName() {
        return name;
    }

    public String getId(){
        return id;
    }

}
