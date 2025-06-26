package com.example.systemedemandecange.Entitie;
import com.example.systemedemandecange.Entitie.DemandeConge;
import com.example.systemedemandecange.Entitie.SoldeConge;
import com.example.systemedemandecange.Entitie.User;
import jakarta.persistence.*;
import com.example.systemedemandecange.Entitie.Role;
import java.util.List;

@Entity
public class Employe extends User {

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<DemandeConge> demandes;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<SoldeConge> soldes;

    public Employe() {}

    public Employe(String name, String prenom, String email, String password, Role role,
                   List<DemandeConge> demandes, List<SoldeConge> soldes) {
        super(name, prenom, email, password, role);
        this.demandes = demandes;
        this.soldes = soldes;
    }

    // Getters & Setters
    public List<DemandeConge> getDemandes() { return demandes; }
    public void setDemandes(List<DemandeConge> demandes) { this.demandes = demandes; }

    public List<SoldeConge> getSoldes() { return soldes; }
    public void setSoldes(List<SoldeConge> soldes) { this.soldes = soldes; }
}
