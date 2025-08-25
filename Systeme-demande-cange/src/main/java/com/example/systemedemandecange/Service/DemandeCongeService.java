package com.example.systemedemandecange.Service;

import com.example.systemedemandecange.DTO.DemandeCongeDTO;
import com.example.systemedemandecange.Entitie.*;
import com.example.systemedemandecange.Repositorie.DemandeCongeRepositorie;
import com.example.systemedemandecange.Repositorie.EmployeRepositorie;
import com.example.systemedemandecange.Repositorie.ManagerRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DemandeCongeService {

    private final DemandeCongeRepositorie demandeCongeRepositorie;
    private final SoldeCongeService soldeCongeService;
    private final EmployeRepositorie employeRepositorie;
    private final ManagerRepositorie managerRepositorie;

    @Autowired
    public DemandeCongeService(DemandeCongeRepositorie demandeCongeRepositorie,
                               SoldeCongeService soldeCongeService,
                               EmployeRepositorie employeRepositorie,
                               ManagerRepositorie managerRepositorie) {
        this.demandeCongeRepositorie = demandeCongeRepositorie;
        this.soldeCongeService = soldeCongeService;
        this.employeRepositorie = employeRepositorie;
        this.managerRepositorie = managerRepositorie;
    }

    public DemandeConge create(DemandeConge demande) {
        Employe employe = employeRepositorie.findById(demande.getEmploye().getId())
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));

        Manager manager = managerRepositorie.findById(demande.getManager().getId())
                .orElseThrow(() -> new RuntimeException("Manager non trouvé"));

        if (demande.getTypeConge() == null) {
            throw new RuntimeException("Type de congé invalide ou manquant.");
        }

        demande.setEmploye(employe);
        demande.setManager(manager);
        demande.setStatut(Statut.En_Attante);
        demande.setDateSoumission(LocalDate.now());

        return demandeCongeRepositorie.save(demande);
    }

    public DemandeConge updatedemande(DemandeConge demande) {
        return demandeCongeRepositorie.save(demande);
    }

    public void delete(Long id) {
        demandeCongeRepositorie.deleteById(id);
    }

    public List<DemandeConge> getAll() {
        return demandeCongeRepositorie.findAll();
    }

    public Optional<DemandeConge> getById(Long id) {
        return demandeCongeRepositorie.findById(id);
    }

    public DemandeConge validerDemande(Long demandeId, Long managerId) {
        DemandeConge demande = demandeCongeRepositorie.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));

        if (demande.getDateDebut() == null || demande.getDateFin() == null) {
            throw new RuntimeException("Les dates de début et de fin sont obligatoires.");
        }

        long nbJours = ChronoUnit.DAYS.between(demande.getDateDebut(), demande.getDateFin()) + 1;
        if (nbJours <= 0) {
            throw new RuntimeException("Dates de congé invalides.");
        }

        boolean success = soldeCongeService.decrementerSolde(
                demande.getEmploye(),
                demande.getTypeConge(),
                (int) nbJours
        );

        if (!success) {
            throw new RuntimeException("Solde insuffisant pour ce type de congé.");
        }

        Manager manager = managerRepositorie.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager non trouvé"));

        demande.setStatut(Statut.ACCEPTER);
        demande.setManager(manager);
        demande.setDateTraitement(LocalDate.now());

        return demandeCongeRepositorie.save(demande);
    }

    public DemandeConge refuseDemande(Long id, Manager manager) {
        DemandeConge demande = demandeCongeRepositorie.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande introuvable"));

        demande.setStatut(Statut.REFUSER);
        demande.setManager(manager);
        demande.setDateTraitement(LocalDate.now());

        return demandeCongeRepositorie.save(demande);
    }

    public DemandeCongeDTO convertToDTO(DemandeConge demande) {
        String employeNom = demande.getEmploye() != null
                ? demande.getEmploye().getName() + " " + demande.getEmploye().getPrenom()
                : null;

        String managerNom = demande.getManager() != null
                ? demande.getManager().getName() + " " + demande.getManager().getPrenom()
                : null;

        return new DemandeCongeDTO(
                demande.getId(),
                demande.getTypeConge(),
                employeNom,
                managerNom,
                demande.getStatut(),
                demande.getDateDebut(),
                demande.getDateFin(),
                demande.getDateSoumission(),
                demande.getDateTraitement(),
                demande.getMotifRecu()
        );
    }

    public List<DemandeCongeDTO> getAllDTOs() {
        return demandeCongeRepositorie.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
