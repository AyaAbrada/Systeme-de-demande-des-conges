package com.example.systemedemandecange.Service;
import com.example.systemedemandecange.Entitie.DemandeConge;
import com.example.systemedemandecange.Entitie.Manager;
import com.example.systemedemandecange.Entitie.Statut;
import com.example.systemedemandecange.Repositorie.DemandeCongeRepositorie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DemandeCongeService {
    private DemandeCongeRepositorie demandeCongeRepositorie;

    public DemandeCongeService(DemandeCongeRepositorie demandeCongeRepositorie){
        this.demandeCongeRepositorie = demandeCongeRepositorie;
    }

    public DemandeConge create(DemandeConge demande){
        return demandeCongeRepositorie.save(demande);
    }
    public List<DemandeConge> getAll(){
        return demandeCongeRepositorie.findAll();
    }
    public Optional<DemandeConge> getById(Long id){
        return demandeCongeRepositorie.findById(id);
    }
    public DemandeConge updatedemande(DemandeConge demande) {
        return demandeCongeRepositorie.save(demande);
    }
    public void delete(Long id){
        demandeCongeRepositorie.deleteById(id);
    }

    public DemandeConge validerDemande(Long id, Manager manager){
        DemandeConge demande = demandeCongeRepositorie.findById(id)
                .orElseThrow(()-> new RuntimeException("Demande introuvable"));

        demande.setStatut(Statut.ACCEPTER); 
        demande.setManager(manager);
        return demandeCongeRepositorie.save(demande);

    }

    public DemandeConge refuseDemande(Long id, Manager manager){
        DemandeConge demande = demandeCongeRepositorie.findById(id)
                .orElseThrow(()-> new RuntimeException("Demande introuvable"));

        demande.setStatut(Statut.REFUSER);
        demande.setManager(manager);
        return demandeCongeRepositorie.save(demande);

    }
}
