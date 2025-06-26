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

    public DemandeConge() {}

    public DemandeConge(TypeConge typeConge, Employe employe) {
        this.typeConge = typeConge;
        this.employe = employe;
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
}
