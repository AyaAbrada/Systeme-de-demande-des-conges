package com.example.systemedemandecange;

import com.example.systemedemandecange.Entitie.*;
import com.example.systemedemandecange.Service.DemandeCongeService;
import com.example.systemedemandecange.Repositorie.DemandeCongeRepositorie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DemandeCongeTests {

    @Autowired
    private DemandeCongeService demandeService;

    @Autowired
    private DemandeCongeRepositorie demandeRepo;

    @Test
    public void testCreateAndGetDemande() {
        DemandeConge demande = new DemandeConge();
        demande.setTypeConge(TypeConge.Maladie);
        demande.setDateDebut(LocalDate.of(2025, 8, 1));
        demande.setDateFin(LocalDate.of(2025, 8, 5));
        demande.setDateSoumission(LocalDate.now());
        demande.setStatut(Statut.En_Attante);
        demande.setMotifRecu("Test maladie");

        DemandeConge saved = demandeService.create(demande);

        assertNotNull(saved.getId());

        Optional<DemandeConge> found = demandeService.getById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals(TypeConge.Maladie, found.get().getTypeConge());
    }

    @Test
    public void testValiderDemande() {
        DemandeConge demande = new DemandeConge();
        demande.setTypeConge(TypeConge.Maladie);
        demande.setDateDebut(LocalDate.of(2025, 9, 10));
        demande.setDateFin(LocalDate.of(2025, 9, 15));
        demande.setDateSoumission(LocalDate.now());
        demande.setStatut(Statut.En_Attante);

        DemandeConge saved = demandeService.create(demande);

        Manager manager = new Manager(); // Peut être simplifié si tu n’as pas encore relié à la BD
        DemandeConge validated = demandeService.validerDemande(saved.getId(), manager.getId());

        assertEquals(Statut.ACCEPTER, validated.getStatut());
        assertEquals(manager, validated.getManager());
    }

    @Test
    public void testRefuserDemande() {
        DemandeConge demande = new DemandeConge();
        demande.setTypeConge(TypeConge.Annuel);
        demande.setDateDebut(LocalDate.of(2025, 10, 1));
        demande.setDateFin(LocalDate.of(2025, 10, 5));
        demande.setDateSoumission(LocalDate.now());
        demande.setStatut(Statut.En_Attante);

        DemandeConge saved = demandeService.create(demande);

        Manager manager = new Manager();
        DemandeConge refused = demandeService.refuseDemande(saved.getId(), manager);

        assertEquals(Statut.REFUSER, refused.getStatut());
    }

    @Test
    public void testDeleteDemande() {
        DemandeConge demande = new DemandeConge();
        demande.setTypeConge(TypeConge.Annuel);
        demande.setDateDebut(LocalDate.of(2025, 11, 1));
        demande.setDateFin(LocalDate.of(2025, 11, 5));
        demande.setDateSoumission(LocalDate.now());
        demande.setStatut(Statut.En_Attante);

        DemandeConge saved = demandeService.create(demande);
        Long id = saved.getId();

        demandeService.delete(id);
        Optional<DemandeConge> deleted = demandeService.getById(id);
        assertFalse(deleted.isPresent());
    }
}
