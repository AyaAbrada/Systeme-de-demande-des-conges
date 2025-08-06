package com.example.systemedemandecange.Controller;
import com.example.systemedemandecange.DTO.DemandeCongeDTO;
import com.example.systemedemandecange.Entitie.DemandeConge;
import com.example.systemedemandecange.Entitie.Manager;
import com.example.systemedemandecange.Service.DemandeCongeService;
import com.example.systemedemandecange.Service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demandes")
@CrossOrigin(origins = "http://localhost:4200")
public class DemandeCongeController {

    private final DemandeCongeService demandeCongeService;
    private final ManagerService managerService;

    public DemandeCongeController(DemandeCongeService demandeCongeService, ManagerService managerService){
        this.demandeCongeService = demandeCongeService;
        this.managerService = managerService;
    }

    @PostMapping
    public DemandeConge create(@RequestBody DemandeConge demande){
        return demandeCongeService.create(demande);
    }

    @GetMapping
    public List<DemandeCongeDTO> getAll(){
        return demandeCongeService.getAllDTOs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeCongeDTO> getById(@PathVariable Long id){
        return demandeCongeService.getById(id)
                .map(demandeCongeService::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public DemandeConge updateDemande(@RequestBody DemandeConge demande) {
        return demandeCongeService.updatedemande(demande);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        demandeCongeService.delete(id);
    }

    @PutMapping("/{id}/valider")
    public ResponseEntity<DemandeCongeDTO> validerDemande(@PathVariable Long id, @RequestParam Long managerId) {
        Manager manager = managerService.findById(managerId);
        DemandeConge result = demandeCongeService.validerDemande(id, manager.getId());
        return ResponseEntity.ok(demandeCongeService.convertToDTO(result));
    }

    @PutMapping("/{id}/refuser")
    public ResponseEntity<DemandeCongeDTO> refuseDemande(@PathVariable Long id, @RequestParam Long managerId) {
        Manager manager = managerService.findById(managerId);
        DemandeConge result = demandeCongeService.refuseDemande(id, manager);
        return ResponseEntity.ok(demandeCongeService.convertToDTO(result));
    }
}
