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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200") // Angular frontend
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
            // EMPLOYE creation requires Manager token
            if ("EMPLOYE".equalsIgnoreCase(request.getRole())) {
                if (authHeader == null || !authHeader.startsWith("Bearer "))
                    return ResponseEntity.status(HttpStatus.FORBIDDEN)
                            .body(Map.of("error", "Token requis pour créer un employé"));

                String token = authHeader.replace("Bearer ", "");
                String currentUsername = jwtUtils.extractUsername(token);
                User currentUser = userRepositorie.findByUsername(currentUsername);

                if (!(currentUser instanceof Manager))
                    return ResponseEntity.status(HttpStatus.FORBIDDEN)
                            .body(Map.of("error", "Seul un manager peut créer un employé"));
            }

            // Username must be unique
            if (userRepositorie.findByUsername(request.getUsername()) != null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Username déjà utilisé"));

            // Encode password
            String encodedPassword = passwordEncoder.encode(request.getPassword());

            // Create user based on role
            User newUser = "EMPLOYE".equalsIgnoreCase(request.getRole())
                    ? new Employe(request.getName(), request.getPrenom(), request.getUsername(), encodedPassword)
                    : new Manager(request.getName(), request.getPrenom(), request.getUsername(), encodedPassword);

            userRepositorie.save(newUser);

            return ResponseEntity.ok(Map.of(
                    "message", "Utilisateur créé avec succès",
                    "username", newUser.getUsername(),
                    "role", request.getRole()
            ));

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur serveur: " + ex.getMessage()));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            User user = userRepositorie.findByUsername(loginRequest.getUsername());
            if (user == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur introuvable");

            // Determine role
            String role = (user instanceof Manager) ? "MANAGER" : "EMPLOYE";
            Long employeId = (user instanceof Employe) ? user.getId() : null;

            // Generate token with roles
            List<String> roles = List.of(role); // wrap role in a list
            String token = jwtUtils.generateJwtToken(user.getUsername(), roles);

            return ResponseEntity.ok(new LoginResponse(token, role, employeId));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Nom d’utilisateur ou mot de passe incorrect");
        }
    }

}
