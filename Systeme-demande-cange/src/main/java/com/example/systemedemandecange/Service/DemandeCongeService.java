package com.example.systemedemandecange.Service;

import com.example.systemedemandecange.DTO.DemandeCongeDTO;
import com.example.systemedemandecange.Entitie.DemandeConge;
import com.example.systemedemandecange.Entitie.Employe;
import com.example.systemedemandecange.Entitie.Manager;
import com.example.systemedemandecange.Entitie.Statut;
import com.example.systemedemandecange.Repositorie.DemandeCongeRepositorie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DemandeCongeService {

    private final DemandeCongeRepositorie demandeCongeRepositorie;

    public DemandeCongeService(DemandeCongeRepositorie demandeCongeRepositorie){
        this.demandeCongeRepositorie = demandeCongeRepositorie;
    }

    public DemandeConge create(DemandeConge demande){
        return demandeCongeRepositorie.save(demande);
    }

    public DemandeConge updatedemande(DemandeConge demande) {
        return demandeCongeRepositorie.save(demande);
    }

    public void delete(Long id){
        demandeCongeRepositorie.deleteById(id);
    }

    public List<DemandeConge> getAll(){
        return demandeCongeRepositorie.findAll();
    }

    public Optional<DemandeConge> getById(Long id){
        return demandeCongeRepositorie.findById(id);
    }

    public DemandeConge validerDemande(Long id, Manager manager){
        DemandeConge demande = demandeCongeRepositorie.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande introuvable"));
        demande.setStatut(Statut.ACCEPTER);
        demande.setManager(manager);
        return demandeCongeRepositorie.save(demande);
    }

    public DemandeConge refuseDemande(Long id, Manager manager){
        DemandeConge demande = demandeCongeRepositorie.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande introuvable"));
        demande.setStatut(Statut.REFUSER);
        demande.setManager(manager);
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
