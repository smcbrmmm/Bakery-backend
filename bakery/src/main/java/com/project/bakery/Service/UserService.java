package com.project.bakery.Service;

import com.project.bakery.Model.User;
import com.project.bakery.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;
import java.util.List;

@Service
public class UserService {

    UserRepository repository;

    public UserService(UserRepository userRepository){
        this.repository = userRepository;
    }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public void createUser(User user){
        repository.save(user);
    }

    private String hash(String pin) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(pin, salt);
    }

    private String signin(String email , String password){
        return "{'token':'test123'}";
    }
}
