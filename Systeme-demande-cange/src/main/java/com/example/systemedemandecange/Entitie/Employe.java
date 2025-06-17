package com.example.systemedemandecange.Entitie;
import java.util.List;

public class Employe extends User {


    private List<String> etapes;
    private List<String> demandes;

    public List<String> getEtapes() {
        return etapes;
    }

    public void setEtapes(List<String> etapes) {
        this.etapes = etapes;
    }

    public List<String> getDemandes() {
        return demandes;
    }

    public void setDemandes(List<String> demandes) {
        this.demandes = demandes;
    }

    public Employe(int id, String name, String prenom, String email, String password, Role role, List<String> etapes, List<String> demandes) {
        super(id, name, prenom, email, password, role);
        this.etapes = etapes;
        this.demandes = demandes;
    }
}
