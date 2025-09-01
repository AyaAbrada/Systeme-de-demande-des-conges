package com.example.systemedemandecange.Entitie;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends User {

    @OneToMany
    @JoinColumn(name = "manager_id")
    @JsonManagedReference
    private List<Employe> equipe = new ArrayList<>();

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DemandeConge> demandesAValider = new ArrayList<>();

    public Manager() {}

    public Manager(String name, String prenom, String username, String password) {
        super(name, prenom, username, password);
    }

    // Getters & Setters
    public List<Employe> getEquipe() { return equipe; }
    public void setEquipe(List<Employe> equipe) { this.equipe = equipe; }

    public List<DemandeConge> getDemandesAValider() { return demandesAValider; }
    public void setDemandesAValider(List<DemandeConge> demandesAValider) {
        this.demandesAValider = demandesAValider;
    }
}
