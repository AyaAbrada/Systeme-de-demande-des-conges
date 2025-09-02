package com.example.systemedemandecange.Controller;
import com.example.systemedemandecange.DTO.DemandeCongeDTO;
import com.example.systemedemandecange.DTO.DemandeCongeRequest;
import com.example.systemedemandecange.Entitie.DemandeConge;
import com.example.systemedemandecange.Entitie.Employe;
import com.example.systemedemandecange.Entitie.Manager;
import com.example.systemedemandecange.Entitie.TypeConge;
import com.example.systemedemandecange.Repositorie.DemandeCongeRepositorie;
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
    private final DemandeCongeRepositorie demandeCongeRepositorie;
    private final ManagerService managerService;
    private final EmployeService employeService;

    public DemandeCongeController(DemandeCongeService demandeCongeService, DemandeCongeRepositorie demandeCongeRepositorie, ManagerService managerService, EmployeService employeService) {
        this.demandeCongeService = demandeCongeService;
        this.demandeCongeRepositorie = demandeCongeRepositorie;
        this.managerService = managerService;
        this.employeService = employeService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DemandeCongeDTO> create(@RequestBody DemandeCongeRequest request) {
        Employe employe = employeService.findById(request.getEmployeId());
        Manager manager = managerService.findById(request.getManagerId());

        DemandeConge demande = new DemandeConge();
        demande.setEmploye(employe);
        demande.setManager(manager);
        demande.setTypeConge(TypeConge.valueOf(request.getTypeConge()));
        demande.setDateDebut(LocalDate.parse(request.getDateDebut()));
        demande.setDateFin(LocalDate.parse(request.getDateFin()));

        DemandeConge saved = demandeCongeService.create(demande);
        return ResponseEntity.ok(demandeCongeService.convertToDTO(saved));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DemandeCongeDTO> getAll() {
        return demandeCongeService.getAllDTOs();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DemandeCongeDTO> getById(@PathVariable Long id) {
        return demandeCongeService.getById(id)
                .map(demandeCongeService::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DemandeConge> updateDemande(@RequestBody DemandeConge demande) {
        return ResponseEntity.ok(demandeCongeService.updatedemande(demande));
    }

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

    // Récupérer toutes les demandes d’un employé par son id
    @GetMapping(value = "/employe/{employeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DemandeCongeDTO> getDemandesByEmploye(@PathVariable Long employeId) {
        return demandeCongeService.getByEmployeId(employeId);
    }



}
