package com.example.systemedemandecange.Controller;
import com.example.systemedemandecange.Entitie.SoldeConge;
import com.example.systemedemandecange.Service.SoldeCongeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solde")
@CrossOrigin(origins = "http://localhost:4200")
public class SoldeCongeController {

    private final SoldeCongeService soldeCongeService;

    public SoldeCongeController(SoldeCongeService soldeCongeService) {
        this.soldeCongeService = soldeCongeService;
    }

    @PostMapping("/init")
    public String initialiserPourTous() {
        soldeCongeService.initialiserPourTousLesEmployes();
        return "Soldes initialisés pour tous les employés.";
    }

    @GetMapping("/{employeId}")
    public List<SoldeConge> getSoldeEmploye(@PathVariable Long employeId) {
        return soldeCongeService.getSoldeParEmploye(employeId);
    }
}
