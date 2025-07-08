package com.example.systemedemandecange.Entitie;
import jakarta.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends User {

    @OneToMany
    @JoinColumn(name = "manager_id")
    private List<Employe> equipe;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<DemandeConge> demandesAValider;

    public Manager() {}

    public Manager(String name, String prenom, String email, String password,
                   List<Employe> equipe, List<DemandeConge> demandesAValider) {
        super(name, prenom, email, password);
        this.equipe = equipe;
        this.demandesAValider = demandesAValider;
    }

    public List<Employe> getEquipe() { return equipe; }
    public void setEquipe(List<Employe> equipe) { this.equipe = equipe; }

    public List<DemandeConge> getDemandesAValider() { return demandesAValider; }
    public void setDemandesAValider(List<DemandeConge> demandesAValider) {
        this.demandesAValider = demandesAValider;
    }
}
