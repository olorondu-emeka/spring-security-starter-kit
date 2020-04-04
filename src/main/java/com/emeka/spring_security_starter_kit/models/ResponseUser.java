package com.emeka.spring_security_starter_kit.models;

public class ResponseUser {
    private String jwt;
    private String role;

    public ResponseUser(String jwt, String role) {
        this.jwt = jwt;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
