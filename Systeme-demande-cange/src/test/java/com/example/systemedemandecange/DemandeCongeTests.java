package com.example.systemedemandecange;
import com.example.systemedemandecange.Entitie.*;
import com.example.systemedemandecange.Repositorie.DemandeCongeRepositorie;
import com.example.systemedemandecange.Repositorie.EmployeRepositorie;
import com.example.systemedemandecange.Repositorie.ManagerRepositorie;
import com.example.systemedemandecange.Service.DemandeCongeService;
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

    @Autowired
    private EmployeRepositorie employeRepo;

    @Autowired
    private ManagerRepositorie managerRepo;

    // Test création et récupération d'une demande
    @Test
    public void testCreateAndGetDemande() {
        Employe employe = employeRepo.save(new Employe("Dupont", "Jean", "dupont", "1234"));
        Manager manager = managerRepo.save(new Manager("Martin", "Paul", "martin", "1234"));

        DemandeConge demande = new DemandeConge();
        demande.setTypeConge(TypeConge.Maladie);
        demande.setDateDebut(LocalDate.of(2025, 8, 1));
        demande.setDateFin(LocalDate.of(2025, 8, 5));
        demande.setDateSoumission(LocalDate.now());
        demande.setStatut(Statut.En_Attante);
        demande.setMotifRecu("Test maladie");
        demande.setEmploye(employe);
        demande.setManager(manager);

        DemandeConge saved = demandeService.create(demande);

        assertNotNull(saved.getId());

        Optional<DemandeConge> found = demandeService.getById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals(TypeConge.Maladie, found.get().getTypeConge());
    }


    // Test refus d'une demande
    @Test
    public void testRefuserDemande() {
        Employe employe = employeRepo.save(new Employe("Petit", "Claire", "claire", "1234"));
        Manager manager = managerRepo.save(new Manager("Moreau", "Julien", "julien", "1234"));

        DemandeConge demande = new DemandeConge();
        demande.setTypeConge(TypeConge.Annuel);
        demande.setDateDebut(LocalDate.of(2025, 10, 1));
        demande.setDateFin(LocalDate.of(2025, 10, 5));
        demande.setDateSoumission(LocalDate.now());
        demande.setStatut(Statut.En_Attante);
        demande.setEmploye(employe);
        demande.setManager(manager);

        DemandeConge saved = demandeService.create(demande);

        DemandeConge refused = demandeService.refuseDemande(saved.getId(), manager);

        assertEquals(Statut.REFUSER, refused.getStatut());
    }

    // Test suppression d'une demande
    @Test
    public void testDeleteDemande() {
        Employe employe = employeRepo.save(new Employe("Lambert", "Sophie", "sophie", "1234"));
        Manager manager = managerRepo.save(new Manager("Bernard", "Hugo", "hugo", "1234"));

        DemandeConge demande = new DemandeConge();
        demande.setTypeConge(TypeConge.Annuel);
        demande.setDateDebut(LocalDate.of(2025, 11, 1));
        demande.setDateFin(LocalDate.of(2025, 11, 5));
        demande.setDateSoumission(LocalDate.now());
        demande.setStatut(Statut.En_Attante);
        demande.setEmploye(employe);
        demande.setManager(manager);

        DemandeConge saved = demandeService.create(demande);
        Long id = saved.getId();

        demandeService.delete(id);
        Optional<DemandeConge> deleted = demandeService.getById(id);
        assertFalse(deleted.isPresent());
    }

    //  Test création sans type de congé
    @Test
    public void testCreateDemandeSansTypeConge_ShouldFail() {
        Employe employe = employeRepo.save(new Employe("Noel", "Laura", "laura", "1234"));
        Manager manager = managerRepo.save(new Manager("Robin", "Yves", "yves", "1234"));

        DemandeConge demande = new DemandeConge();
        demande.setEmploye(employe);
        demande.setManager(manager);
        demande.setDateDebut(LocalDate.of(2025, 12, 1));
        demande.setDateFin(LocalDate.of(2025, 12, 5));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> demandeService.create(demande));

        assertEquals("Type de congé invalide ou manquant.", exception.getMessage());
    }

    //Test validation avec dates invalides
    @Test
    public void testValiderDemandeAvecDatesInvalides_ShouldFail() {
        Employe employe = employeRepo.save(new Employe("Robert", "Emma", "emma", "1234"));
        Manager manager = managerRepo.save(new Manager("Garcia", "Louis", "louis", "1234"));

        DemandeConge demande = new DemandeConge();
        demande.setTypeConge(TypeConge.Annuel);
        demande.setEmploye(employe);
        demande.setManager(manager);
        demande.setDateDebut(LocalDate.of(2025, 12, 10));
        demande.setDateFin(LocalDate.of(2025, 12, 5)); // fin avant début
        demande.setDateSoumission(LocalDate.now());

        DemandeConge saved = demandeRepo.save(demande);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> demandeService.validerDemande(saved.getId(), manager.getId()));

        assertEquals("Dates de congé invalides.", exception.getMessage());
    }


}
