package com.example.systemedemandecange.Service;

import com.example.systemedemandecange.Entitie.Employe;
import com.example.systemedemandecange.Entitie.Manager;
import com.example.systemedemandecange.Entitie.User;
import com.example.systemedemandecange.Repositorie.UserRepositorie;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepositorie userRepositorie;

    public CustomUserDetailService(UserRepositorie userRepositorie) {
        this.userRepositorie = userRepositorie;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositorie.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");

        String role = (user instanceof Manager) ? "MANAGER" : "EMPLOYE";

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
        );
    }
}
