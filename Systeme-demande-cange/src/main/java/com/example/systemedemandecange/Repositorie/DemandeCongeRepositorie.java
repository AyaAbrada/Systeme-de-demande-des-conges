package com.example.systemedemandecange.Repositorie;
import com.example.systemedemandecange.Entitie.DemandeConge;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DemandeCongeRepositorie extends JpaRepository<DemandeConge, Long>{
     List<DemandeConge> findByEmployeId(Long employeId);

}
