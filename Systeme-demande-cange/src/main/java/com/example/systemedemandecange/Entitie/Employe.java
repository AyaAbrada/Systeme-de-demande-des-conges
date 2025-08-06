package com.example.systemedemandecange.Entitie;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("EMPLOYE")
public class Employe extends User {

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DemandeConge> demandes;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SoldeConge> soldes;

    public Employe() {}

    public Employe(String name, String prenom, String username, String password,
                   List<DemandeConge> demandes, List<SoldeConge> soldes) {
        super(name, prenom, username, password);
        this.demandes = demandes;
        this.soldes = soldes;
    }

    // Getters & Setters
    public List<DemandeConge> getDemandes() { return demandes; }
    public void setDemandes(List<DemandeConge> demandes) { this.demandes = demandes; }

    public List<SoldeConge> getSoldes() { return soldes; }
    public void setSoldes(List<SoldeConge> soldes) { this.soldes = soldes; }
}
