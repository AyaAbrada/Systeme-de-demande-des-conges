package com.example.systemedemandecange.Entitie;
import com.example.systemedemandecange.Entitie.Employe;
import com.example.systemedemandecange.Entitie.TypeConge;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class SoldeConge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private TypeConge typeConge;

    @Enumerated(EnumType.STRING)
    private Statut statu;

    private String motifRecu;
    private LocalDate dateSoumission;
    private LocalDate dateTraitement;

    public SoldeConge() {}

    public SoldeConge(Employe employe, LocalDate dateDebut, LocalDate dateFin, TypeConge typeConge, Statut statu,
                      String motifRecu, LocalDate dateSoumission, LocalDate dateTraitement) {
        this.employe = employe;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeConge = typeConge;
        this.statu = statu;
        this.motifRecu = motifRecu;
        this.dateSoumission = dateSoumission;
        this.dateTraitement = dateTraitement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public TypeConge getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(TypeConge typeConge) {
        this.typeConge = typeConge;
    }

    public Statut getStatu() {
        return statu;
    }

    public void setStatu(Statut statu) {
        this.statu = statu;
    }

    public String getMotifRecu() {
        return motifRecu;
    }

    public void setMotifRecu(String motifRecu) {
        this.motifRecu = motifRecu;
    }

    public LocalDate getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(LocalDate dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public LocalDate getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(LocalDate dateTraitement) {
        this.dateTraitement = dateTraitement;
    }
}
