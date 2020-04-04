package com.emeka.spring_security_starter_kit.models;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class GeneralUser {
    private String email;
    private String password;
    private String role;

    public GeneralUser() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
