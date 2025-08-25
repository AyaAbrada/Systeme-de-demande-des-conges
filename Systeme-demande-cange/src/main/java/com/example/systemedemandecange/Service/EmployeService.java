package com.example.systemedemandecange.Service;

import com.example.systemedemandecange.Entitie.Employe;
import com.example.systemedemandecange.Repositorie.EmployeRepositorie;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeService {

    private final EmployeRepositorie employeRepository;

    public EmployeService(EmployeRepositorie employeRepository) {
        this.employeRepository = employeRepository;
    }

    // Récupérer un employé par ID
    public Employe findById(Long id) {
        Optional<Employe> employeOpt = employeRepository.findById(id);
        if (employeOpt.isPresent()) {
            return employeOpt.get();
        } else {
            throw new RuntimeException("Employé non trouvé avec l'ID : " + id);
        }
    }

    // Sauvegarder un employé
    public Employe save(Employe employe) {
        return employeRepository.save(employe);
    }

    // Récupérer tous les employés
    public Iterable<Employe> getAll() {
        return employeRepository.findAll();
    }
}
