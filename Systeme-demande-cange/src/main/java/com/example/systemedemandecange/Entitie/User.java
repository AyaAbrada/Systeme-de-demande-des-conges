package com.example.systemedemandecange.Entitie;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class User {
        public int id;
        public String name;
        public String prenom;
        public String email;
        public String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(int id, String name, String prenom, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
