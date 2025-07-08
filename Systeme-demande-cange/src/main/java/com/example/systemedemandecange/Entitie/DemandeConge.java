package com.example.systemedemandecange.Entitie;
import jakarta.persistence.*;

@Entity
public class DemandeConge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeConge typeConge;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.En_Attante;


    public DemandeConge() {}

    public DemandeConge(TypeConge typeConge, Employe employe , Manager manager) {
        this.typeConge = typeConge;
        this.employe = employe;
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Long getId() {
        return id; }
    public void setId(Long id) {
        this.id = id; }

    public TypeConge getTypeConge() {
        return typeConge; }
    public void setTypeConge(TypeConge typeConge) {
        this.typeConge = typeConge; }

    public Employe getEmploye() {
        return employe; }
    public void setEmploye(Employe employe) {
        this.employe = employe; }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
