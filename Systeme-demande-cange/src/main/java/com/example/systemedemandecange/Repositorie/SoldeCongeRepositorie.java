package com.example.systemedemandecange.Repositorie;

import com.example.systemedemandecange.Entitie.Employe;
import com.example.systemedemandecange.Entitie.SoldeConge;
import com.example.systemedemandecange.Entitie.TypeConge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldeCongeRepositorie extends JpaRepository<SoldeConge, Long> {

    SoldeConge findByEmployeAndTypeConge(Employe employe, TypeConge typeConge);

    List<SoldeConge> findByEmployeId(Long employeId);
}
