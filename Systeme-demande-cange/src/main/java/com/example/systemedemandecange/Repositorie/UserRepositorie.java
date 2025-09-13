package com.example.systemedemandecange.Repositorie;
import com.example.systemedemandecange.Entitie.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositorie extends JpaRepository <User, Long>{
    User findByUsername(String username);

    boolean existsByUsername(String username);

}
