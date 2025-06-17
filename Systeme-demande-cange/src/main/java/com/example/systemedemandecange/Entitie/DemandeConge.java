package com.example.systemedemandecange.Entitie;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class DemandeConge {
    public Long id ;
    @Enumerated(EnumType.STRING)
    private TypeConge typeConge;
    public Employe employe ;

    public DemandeConge(Long id, TypeConge typeConge, Employe employe) {
        this.id = id;
        this.typeConge = typeConge;
        this.employe = employe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeConge getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(TypeConge typeConge) {
        this.typeConge = typeConge;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}
