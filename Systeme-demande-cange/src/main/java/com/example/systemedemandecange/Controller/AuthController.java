package com.example.systemedemandecange.Controller;
import com.example.systemedemandecange.Configiration.JwtUtils;
import com.example.systemedemandecange.Entitie.Employe;
import com.example.systemedemandecange.Entitie.Manager;
import com.example.systemedemandecange.Entitie.User;
import com.example.systemedemandecange.Repositorie.UserRepositorie;
import com.example.systemedemandecange.Service.UserService;
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
    private final UserService userService;
    private final JwtUtils jwtUtils;


    public AuthController(UserRepositorie userRepositorie, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserService userService, JwtUtils jwtUtils){
        this.userRepositorie = userRepositorie;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        if (userRepositorie.findByUsername(request.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user;
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        if ("EMPLOYE".equalsIgnoreCase(request.getRole())) {
            user = new Employe(request.getName(), request.getPrenom(), request.getUsername(), encodedPassword, null, null);
        } else if ("MANAGER".equalsIgnoreCase(request.getRole())) {
            user = new Manager(request.getName(), request.getPrenom(), request.getUsername(), encodedPassword, null, null);
        } else {
            return ResponseEntity.badRequest().body("Invalid role specified");
        }

        userRepositorie.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            String token = jwtUtils.generateJwtToken(loginRequest.getUsername());

            User user = userRepositorie.findByUsername(loginRequest.getUsername());
            String role = "";
            if (user instanceof Manager) role = "MANAGER";
            else if (user instanceof Employe) role = "EMPLOYE";

            return ResponseEntity.ok(new LoginResponse(token, role));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


}
