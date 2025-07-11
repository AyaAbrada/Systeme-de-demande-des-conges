package com.example.systemedemandecange.Service;
import com.example.systemedemandecange.Entitie.User;
import com.example.systemedemandecange.Repositorie.UserRepositorie;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private UserRepositorie userRepositorie;

    public UserService(UserRepositorie userRepositorie) {
        this.userRepositorie = userRepositorie;
    }

    public List<User> allUser() {
        return userRepositorie.findAll();
    }

}
