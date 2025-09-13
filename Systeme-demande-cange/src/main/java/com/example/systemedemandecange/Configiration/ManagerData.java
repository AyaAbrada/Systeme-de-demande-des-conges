package com.example.systemedemandecange.Configiration;

import com.example.systemedemandecange.Entitie.Manager;
import com.example.systemedemandecange.Repositorie.UserRepositorie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class ManagerData implements CommandLineRunner {

    private final UserRepositorie userRepository;
    private final PasswordEncoder passwordEncoder;

    public ManagerData(UserRepositorie userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        String managerUsername = "ayaabrada";

        // تحقق من وجود المدير
        if (!userRepository.existsByUsername(managerUsername)) {
            Manager manager = new Manager(
                    "Aya",              // name
                    "Abrada",           // prenom
                    managerUsername,    // username
                    passwordEncoder.encode("ayaabrada")  // password مشفر
            );

            userRepository.save(manager);
            System.out.println("Manager créé automatiquement avec username ayaabrada");
        } else {
            System.out.println("Manager déjà présent en base.");
        }
    }
}
