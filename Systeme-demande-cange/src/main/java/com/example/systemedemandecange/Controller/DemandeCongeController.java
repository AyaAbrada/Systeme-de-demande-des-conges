package com.example.systemedemandecange.Controller;
import com.example.systemedemandecange.Entitie.DemandeConge;
import com.example.systemedemandecange.Service.DemandeCongeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/demandes")
public class DemandeCongeController {

    private final DemandeCongeService demandeCongeService;

    public DemandeCongeController(DemandeCongeService demandeCongeService){
        this.demandeCongeService = demandeCongeService;
    }

    @PostMapping
    public DemandeConge create(@RequestBody DemandeConge demande){
        return demandeCongeService.create(demande);
    }

    @GetMapping
    public List<DemandeConge> getAll(){
        return demandeCongeService.getAll();
    }

    @GetMapping("/{id}")
    public DemandeConge getById(@PathVariable Long id){
        return demandeCongeService.getById(id).orElse(null);
    }

}
