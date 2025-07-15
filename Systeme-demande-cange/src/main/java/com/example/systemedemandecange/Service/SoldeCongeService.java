package com.example.systemedemandecange.Service;
import com.example.systemedemandecange.Entitie.Employe;
import com.example.systemedemandecange.Entitie.SoldeConge;
import com.example.systemedemandecange.Entitie.TypeConge;
import com.example.systemedemandecange.Repositorie.SoldeCongeRepositorie;
import com.example.systemedemandecange.Repositorie.EmployeRepositorie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldeCongeService {

    private final SoldeCongeRepositorie soldeCongeRepository;
    private final EmployeRepositorie employeRepository;

    public SoldeCongeService(SoldeCongeRepositorie soldeCongeRepository, EmployeRepositorie employeRepository) {
        this.soldeCongeRepository = soldeCongeRepository;
        this.employeRepository = employeRepository;
    }

    // Initialisation du solde pour un employé
    public void initialiserSoldePourEmploye(Employe employe) {
        soldeCongeRepository.save(new SoldeConge(employe, TypeConge.Annuel, 30)); // 30 jours/an
        soldeCongeRepository.save(new SoldeConge(employe, TypeConge.Maladie, 9999)); // Maladie illimité
    }

    // Initialiser tous les employés
    public void initialiserPourTousLesEmployes() {
        List<Employe> employes = employeRepository.findAll();
        for (Employe e : employes) {
            if (soldeCongeRepository.findByEmployeAndTypeConge(e, TypeConge.Annuel) == null) {
                initialiserSoldePourEmploye(e);
            }
        }
    }

    // Obtenir le solde par employé
    public List<SoldeConge> getSoldeParEmploye(Long employeId) {
        return soldeCongeRepository.findByEmployeId(employeId);
    }

    // Décrémenter les jours restants
    public boolean decrementerSolde(Employe employe, TypeConge typeConge, int joursDemandes) {
        SoldeConge solde = soldeCongeRepository.findByEmployeAndTypeConge(employe, typeConge);
        if (solde == null || solde.getJoursRestants() < joursDemandes) {
            return false;
        }
        solde.setJoursRestants(solde.getJoursRestants() - joursDemandes);
        soldeCongeRepository.save(solde);
        return true;
    }
}
