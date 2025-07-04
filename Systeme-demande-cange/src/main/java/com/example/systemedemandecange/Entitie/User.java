package com.example.systemedemandecange.Entitie;
import jakarta.persistence.*;

@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String prenom;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {}

    public User(String name, String prenom, String email, String password, Role role) {
        this.name = name;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters & Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
