package com.example.systemedemandecange.Entitie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class DemandeConge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeConge typeConge;

    // éviter les boucles infinies
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employe_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Employe employe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Manager manager;

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.En_Attante;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private LocalDate dateSoumission;
    private LocalDate dateTraitement;

    private String motifRecu;

    public DemandeConge() {}

    public DemandeConge(TypeConge typeConge, Employe employe, Manager manager,
                        LocalDate dateDebut, LocalDate dateFin, LocalDate dateSoumission) {
        this.typeConge = typeConge;
        this.employe = employe;
        this.manager = manager;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dateSoumission = dateSoumission;
        this.statut = Statut.En_Attante;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TypeConge getTypeConge() { return typeConge; }
    public void setTypeConge(TypeConge typeConge) { this.typeConge = typeConge; }

    public Employe getEmploye() { return employe; }
    public void setEmploye(Employe employe) { this.employe = employe; }

    public Manager getManager() { return manager; }
    public void setManager(Manager manager) { this.manager = manager; }

    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public LocalDate getDateSoumission() { return dateSoumission; }
    public void setDateSoumission(LocalDate dateSoumission) { this.dateSoumission = dateSoumission; }

    public LocalDate getDateTraitement() { return dateTraitement; }
    public void setDateTraitement(LocalDate dateTraitement) { this.dateTraitement = dateTraitement; }

    public String getMotifRecu() { return motifRecu; }
    public void setMotifRecu(String motifRecu) { this.motifRecu = motifRecu; }
}
