package com.example.systemedemandecange.Controller;

import com.example.systemedemandecange.Configiration.JwtUtils;
import com.example.systemedemandecange.Entitie.Employe;
import com.example.systemedemandecange.Entitie.Manager;
import com.example.systemedemandecange.Entitie.User;
import com.example.systemedemandecange.Repositorie.UserRepositorie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserRepositorie userRepositorie;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthController(UserRepositorie userRepositorie,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtUtils jwtUtils) {
        this.userRepositorie = userRepositorie;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request,
                                          @RequestHeader(value = "Authorization", required = false) String authHeader) {
        try {
            // Seul un manager peut créer un employé
            if ("EMPLOYE".equalsIgnoreCase(request.getRole())) {
                if (authHeader == null) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token requis pour créer un employé");
                }
                String token = authHeader.replace("Bearer ", "");
                String currentUsername = jwtUtils.extractUsername(token);
                User currentUser = userRepositorie.findByUsername(currentUsername);
                if (!(currentUser instanceof Manager)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN)
                            .body("Seul un manager peut créer un compte employé");
                }
            }

            // Vérification username unique
            if (userRepositorie.findByUsername(request.getUsername()) != null) {
                return ResponseEntity.badRequest().body("Username already exists");
            }

            // Encodage mot de passe
            String encodedPassword = passwordEncoder.encode(request.getPassword());

            User newUser;
            if ("EMPLOYE".equalsIgnoreCase(request.getRole())) {
                newUser = new Employe(request.getName(), request.getPrenom(),
                        request.getUsername(), encodedPassword, null, null);
            } else if ("MANAGER".equalsIgnoreCase(request.getRole())) {
                newUser = new Manager(request.getName(), request.getPrenom(),
                        request.getUsername(), encodedPassword, null, null);
            } else {
                return ResponseEntity.badRequest().body("Invalid role specified");
            }

            userRepositorie.save(newUser);
            return ResponseEntity.ok("Utilisateur créé avec succès");

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création du compte: " + ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Authentification Spring Security
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // Récupération utilisateur
            User user = userRepositorie.findByUsername(loginRequest.getUsername());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur introuvable");
            }

            // Génération JWT
            String token = jwtUtils.generateJwtToken(user.getUsername());

            // Déterminer rôle
            String role;
            Long employeId = null;
            if (user instanceof Manager) {
                role = "MANAGER";
            } else {
                role = "EMPLOYE";
                employeId = user.getId();
            }

            return ResponseEntity.ok(new LoginResponse(token, role, employeId));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Nom d’utilisateur ou mot de passe incorrect");
        }
    }
}
