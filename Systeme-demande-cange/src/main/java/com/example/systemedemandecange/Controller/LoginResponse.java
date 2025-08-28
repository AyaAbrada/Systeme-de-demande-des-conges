package com.example.systemedemandecange.Controller;

public class LoginResponse {
    private String token;
    private String role;
    private Long employeId;

    public LoginResponse(String token, String role, Long employeId) {
        this.token = token;
        this.role = role;
        this.employeId = employeId;
    }

    // Getters & Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }
}
