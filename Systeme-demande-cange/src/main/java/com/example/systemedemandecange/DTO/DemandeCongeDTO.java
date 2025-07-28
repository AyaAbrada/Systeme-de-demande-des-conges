package com.example.systemedemandecange.DTO;

import com.example.systemedemandecange.Entitie.Statut;
import com.example.systemedemandecange.Entitie.TypeConge;

import java.time.LocalDate;

public class DemandeCongeDTO {

    private Long id;
    private TypeConge typeConge;
    private String employeNomComplet;
    private String managerNomComplet;
    private Statut statut;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private LocalDate dateSoumission;
    private LocalDate dateTraitement;
    private String motifRecu;

    public DemandeCongeDTO() {}

    public DemandeCongeDTO(Long id, TypeConge typeConge, String employeNomComplet, String managerNomComplet,
                           Statut statut, LocalDate dateDebut, LocalDate dateFin,
                           LocalDate dateSoumission, LocalDate dateTraitement, String motifRecu) {
        this.id = id;
        this.typeConge = typeConge;
        this.employeNomComplet = employeNomComplet;
        this.managerNomComplet = managerNomComplet;
        this.statut = statut;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dateSoumission = dateSoumission;
        this.dateTraitement = dateTraitement;
        this.motifRecu = motifRecu;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TypeConge getTypeConge() { return typeConge; }
    public void setTypeConge(TypeConge typeConge) { this.typeConge = typeConge; }

    public String getEmployeNomComplet() { return employeNomComplet; }
    public void setEmployeNomComplet(String employeNomComplet) { this.employeNomComplet = employeNomComplet; }

    public String getManagerNomComplet() { return managerNomComplet; }
    public void setManagerNomComplet(String managerNomComplet) { this.managerNomComplet = managerNomComplet; }

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
