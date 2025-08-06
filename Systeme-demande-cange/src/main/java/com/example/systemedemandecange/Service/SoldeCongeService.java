package com.example.systemedemandecange.Service;
import com.example.systemedemandecange.Entitie.Employe;
import com.example.systemedemandecange.Entitie.SoldeConge;
import com.example.systemedemandecange.Entitie.TypeConge;
import com.example.systemedemandecange.Repositorie.EmployeRepositorie;
import com.example.systemedemandecange.Repositorie.SoldeCongeRepositorie;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SoldeCongeService {

    private final SoldeCongeRepositorie soldeCongeRepository;
    private final EmployeRepositorie employeRepository;

    public SoldeCongeService(SoldeCongeRepositorie soldeCongeRepository, EmployeRepositorie employeRepository) {
        this.soldeCongeRepository = soldeCongeRepository;
        this.employeRepository = employeRepository;
    }

    // Initialiser solde pour un employé
    public void initialiserSoldePourEmploye(Employe employe) {
        soldeCongeRepository.save(new SoldeConge(employe, TypeConge.Annuel, 30));     // 30 jours
        soldeCongeRepository.save(new SoldeConge(employe, TypeConge.Maladie, 9999));  // illimité
    }

    // Initialiser pour tous les employés
    public void initialiserPourTousLesEmployes() {
        List<Employe> employes = employeRepository.findAll();
        for (Employe e : employes) {
            Optional<SoldeConge> existingSolde = Optional.ofNullable(soldeCongeRepository.findByEmployeAndTypeConge(e, TypeConge.Annuel));
            if (existingSolde.isEmpty()) {
                initialiserSoldePourEmploye(e);
            }
        }
    }

    // Obtenir solde d’un employé
    public List<SoldeConge> getSoldeParEmploye(Long employeId) {
        return soldeCongeRepository.findByEmployeId(employeId);
    }

    // Décrémenter solde
    public boolean decrementerSolde(Employe employe, TypeConge typeConge, int nbJours) {
        Optional<SoldeConge> optionalSolde = Optional.ofNullable(soldeCongeRepository.findByEmployeAndTypeConge(employe, typeConge));

        if (optionalSolde.isPresent()) {
            SoldeConge solde = optionalSolde.get();
            if (solde.getJoursRestants() >= nbJours) {
                solde.setJoursRestants(solde.getJoursRestants() - nbJours);
                soldeCongeRepository.save(solde);
                return true;
            }
        }

        return false;
    }
}
