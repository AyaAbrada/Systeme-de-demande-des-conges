package com.example.systemedemandecange.Service;
import com.example.systemedemandecange.Entitie.Manager;
import com.example.systemedemandecange.Repositorie.ManagerRepositorie;
import org.springframework.stereotype.Service;


@Service
public class ManagerService {
    private final ManagerRepositorie managerRepositorie;

    public ManagerService(ManagerRepositorie managerRepositorie) {
        this.managerRepositorie = managerRepositorie;
    }

    public Manager findById(Long id) {
        return managerRepositorie.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager introuvable"));
    }
}
