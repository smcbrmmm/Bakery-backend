package com.project.bakery.service;

import com.project.bakery.model.User;
import com.project.bakery.exception.BaseException;
import com.project.bakery.exception.UserException;
import com.project.bakery.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public User login(String email , String password) {
        return repository.login(email, password);
    }

    public User loginByLine(String email) {
        return repository.loginByLine(email);
    }

    public User createUser(String email, String password, String name) throws BaseException {
        if (Objects.isNull(email)) {
            throw UserException.createEmailNull();
        }

        if (Objects.isNull(password)) {
            throw UserException.createNameNull();
        }

        if (Objects.isNull(name)) {
            throw UserException.createPasswordNull();
        }
//        if (repository.existsByEmail(email)) {
//            throw UserException.createEmailDuplicated();
//        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setPassword(password);
        user.setName(name);
        return repository.save(user);
    }

    public int isHave(String email) throws SQLException {
        return repository.isHave(email);
    }



//    public void createUserByLine(String email, String tokenId, String name) throws BaseException {
//        if (Objects.isNull(email)) {
//            throw UserException.createEmailNull();
//        }
//
//        if (Objects.isNull(tokenId)) {
//            throw UserException.createTokenIdNull();
//        }
//
//        if (Objects.isNull(name)) {
//            throw UserException.createPasswordNull();
//        }
////        if (repository.existsByEmail(email)) {
////            throw UserException.createEmailDuplicated();
////        }
//        User user = new User();
//        user.setEmail(email);
//        user.setTokenId(tokenId);
//        user.setName(name);
//        repository.saveLine(user);
//    }
}
