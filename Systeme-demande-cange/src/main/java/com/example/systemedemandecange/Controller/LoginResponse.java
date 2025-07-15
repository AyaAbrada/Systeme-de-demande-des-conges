package com.example.systemedemandecange.Controller;

public class LoginResponse {
    private String token;

    public LoginResponse(String token, String role) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
