package com.example.systemedemandecange.Entitie;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("EMPLOYE")
public class Employe extends User {

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DemandeConge> demandes = new ArrayList<>();

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SoldeConge> soldes = new ArrayList<>();

    public Employe() {}

    public Employe(String name, String prenom, String username, String password) {
        super(name, prenom, username, password);
    }

    // Getters & setters
    public List<DemandeConge> getDemandes() { return demandes; }
    public void setDemandes(List<DemandeConge> demandes) { this.demandes = demandes; }

    public List<SoldeConge> getSoldes() { return soldes; }
    public void setSoldes(List<SoldeConge> soldes) { this.soldes = soldes; }
}
