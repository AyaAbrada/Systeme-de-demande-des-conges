package com.example.systemedemandecange.Entitie;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class SoldeConge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Employe employe;

    @Enumerated(EnumType.STRING)
    private TypeConge typeConge;

    private int joursRestants;

    public SoldeConge() {}

    public SoldeConge(Employe employe, TypeConge typeConge, int joursRestants) {
        this.employe = employe;
        this.typeConge = typeConge;
        this.joursRestants = joursRestants;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public TypeConge getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(TypeConge typeConge) {
        this.typeConge = typeConge;
    }

    public int getJoursRestants() {
        return joursRestants;
    }

    public void setJoursRestants(int joursRestants) {
        this.joursRestants = joursRestants;
    }
}
