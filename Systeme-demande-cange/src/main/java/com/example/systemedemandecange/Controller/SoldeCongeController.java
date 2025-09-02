package com.example.systemedemandecange.Controller;
import com.example.systemedemandecange.Entitie.SoldeConge;
import com.example.systemedemandecange.Service.SoldeCongeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/solde")
@CrossOrigin(origins = "http://localhost:4200")
public class SoldeCongeController {

    private final SoldeCongeService soldeCongeService;

    public SoldeCongeController(SoldeCongeService soldeCongeService) {
        this.soldeCongeService = soldeCongeService;
    }

    @PostMapping("/init")
    public ResponseEntity<?> initialiserPourTous() {
        soldeCongeService.initialiserPourTousLesEmployes();
        return ResponseEntity.ok(Map.of("message", "Soldes initialisés pour tous les employés."));
    }


    @GetMapping("/{employeId}")
    public List<SoldeConge> getSoldeEmploye(@PathVariable Long employeId) {
        return soldeCongeService.getSoldeParEmploye(employeId);
    }

    @GetMapping("/username/{username}")
    public List<SoldeConge> getSoldeParUsername(@PathVariable String username) {
        return soldeCongeService.getSoldeParUsername(username);
    }


}
