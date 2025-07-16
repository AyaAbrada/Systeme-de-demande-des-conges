package com.example.systemedemandecange;
import com.example.systemedemandecange.Controller.DemandeCongeController;
import com.example.systemedemandecange.Entitie.*;
import com.example.systemedemandecange.Service.ManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SystemeDemandeCangeApplicationTests {


    @Test
    public void testDemandeCongeGettersSetters() {
        Employe employe = new Employe();
        Manager manager = new Manager();

        LocalDate debut = LocalDate.of(2025, 7, 20);
        LocalDate fin = LocalDate.of(2025, 7, 25);
        LocalDate soumission = LocalDate.of(2025, 7, 15);

        DemandeConge demande = new DemandeConge();
        demande.setId(1L);
        demande.setTypeConge(TypeConge.Annuel);

        demande.setEmploye(employe);
        demande.setManager(manager);
        demande.setDateDebut(debut);
        demande.setDateFin(fin);
        demande.setDateSoumission(soumission);
        demande.setStatut(Statut.En_Attante);
        demande.setMotifRecu("Motif de test");

        assertEquals(1L, demande.getId());
        assertEquals(TypeConge.Annuel, demande.getTypeConge());
        assertEquals(employe, demande.getEmploye());
        assertEquals(manager, demande.getManager());
        assertEquals(debut, demande.getDateDebut());
        assertEquals(fin, demande.getDateFin());
        assertEquals(soumission, demande.getDateSoumission());
        assertEquals(Statut.En_Attante, demande.getStatut());
        assertEquals("Motif de test", demande.getMotifRecu());
    }
}
