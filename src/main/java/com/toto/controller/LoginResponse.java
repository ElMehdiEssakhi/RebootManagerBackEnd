package com.toto.controller;

public class LoginResponse {
    private String role;

    public LoginResponse(String role, String name) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
