package com.example.systemedemandecange.Controller;

import com.example.systemedemandecange.DTO.DemandeCongeDTO;
import com.example.systemedemandecange.DTO.DemandeCongeRequest;
import com.example.systemedemandecange.Entitie.DemandeConge;
import com.example.systemedemandecange.Entitie.Employe;
import com.example.systemedemandecange.Entitie.Manager;
import com.example.systemedemandecange.Entitie.TypeConge;
import com.example.systemedemandecange.Service.DemandeCongeService;
import com.example.systemedemandecange.Service.EmployeService;
import com.example.systemedemandecange.Service.ManagerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/demandes")
@CrossOrigin(origins = "http://localhost:4200")
public class DemandeCongeController {

    private final DemandeCongeService demandeCongeService;
    private final ManagerService managerService;
    private final EmployeService employeService;

    public DemandeCongeController(DemandeCongeService demandeCongeService, ManagerService managerService, EmployeService employeService) {
        this.demandeCongeService = demandeCongeService;
        this.managerService = managerService;
        this.employeService = employeService;
    }

    //  Créer une demande avec DTO de requête
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DemandeCongeDTO> create(@RequestBody DemandeCongeRequest request) {
        // Récupérer les entités existantes
        Employe employe = employeService.findById(request.getEmployeId());
        Manager manager = managerService.findById(request.getManagerId());

        // Construire la demande
        DemandeConge demande = new DemandeConge();
        demande.setEmploye(employe);
        demande.setManager(manager);
        demande.setTypeConge(TypeConge.valueOf(request.getTypeConge()));
        demande.setDateDebut(LocalDate.parse(request.getDateDebut()));
        demande.setDateFin(LocalDate.parse(request.getDateFin()));

        // Sauvegarder et retourner le DTO
        DemandeConge saved = demandeCongeService.create(demande);
        return ResponseEntity.ok(demandeCongeService.convertToDTO(saved));
    }

    //  Récupérer toutes les demandes
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DemandeCongeDTO> getAll() {
        return demandeCongeService.getAllDTOs();
    }

    //  Récupérer une demande par ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DemandeCongeDTO> getById(@PathVariable Long id) {
        return demandeCongeService.getById(id)
                .map(demandeCongeService::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  Mettre à jour une demande
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DemandeConge> updateDemande(@RequestBody DemandeConge demande) {
        return ResponseEntity.ok(demandeCongeService.updatedemande(demande));
    }

    //  Supprimer une demande
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        demandeCongeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //  Valider une demande
    @PutMapping(value = "/{id}/valider", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DemandeCongeDTO> validerDemande(@PathVariable Long id, @RequestParam Long managerId) {
        Manager manager = managerService.findById(managerId);
        DemandeConge result = demandeCongeService.validerDemande(id, manager.getId());
        return ResponseEntity.ok(demandeCongeService.convertToDTO(result));
    }

    //  Refuser une demande
    @PutMapping(value = "/{id}/refuser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DemandeCongeDTO> refuseDemande(@PathVariable Long id, @RequestParam Long managerId) {
        Manager manager = managerService.findById(managerId);
        DemandeConge result = demandeCongeService.refuseDemande(id, manager);
        return ResponseEntity.ok(demandeCongeService.convertToDTO(result));
    }
}
