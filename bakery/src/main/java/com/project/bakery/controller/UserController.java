package com.project.bakery.controller;

import com.project.bakery.business.UserBusiness;
import com.project.bakery.exception.BaseException;
import com.project.bakery.model.*;
import com.project.bakery.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

//@CrossOrigin("https://bakery-frontend-react.vercel.app/")
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserBusiness userBusiness;
    public UserService userService;

    public UserController(UserBusiness userBusiness , UserService userService) {
        this.userBusiness = userBusiness;
        this.userService = userService;
    }

    @GetMapping
    public String Test() {
        return "two";
    }

    @PostMapping("/login")
    public MLoginResponse login(@RequestBody MLoginRequest req){
        User user = userService.login(req.getEmail() , req.getPassword());
        MLoginResponse response = new MLoginResponse();
        response.setUser(user);
        response.setStatus("ok");
        response.setAccessToken(user.getAccessToken());
        response.setMessage("Logged in");
        return response;
    }

    @GetMapping("/isHave/{email}")
    public int isHave(@PathVariable("email") String email) throws SQLException {
        return userService.isHave(email);
    }

    @GetMapping("/loginbyline/{email}")
    public MLoginResponse loginByLine(@PathVariable("email") String email){
        MLoginResponse response = new MLoginResponse();
        if(email.equals("undefined")){
            response.setStatus("no ok");
        }else{
            User user = userService.loginByLine(email);
            response.setUser(user);
            response.setStatus("ok");
            response.setAccessToken(user.getAccessToken());
            response.setMessage("Logged in");
        }
        return response;

    }

    @PostMapping
    @RequestMapping("/register")
    public MRegisterResponse register(@RequestBody MRegisterRequest request) throws BaseException {

        User user = userService.createUser(request.getEmail(),request.getName() , request.getAccessToken());
        MRegisterResponse response = new MRegisterResponse();
        System.out.println(user);
        response.setUser(user);
        response.setStatus("ok");
        response.setAccessToken(user.getAccessToken());
        response.setMessage("Logged in");
        return response;
    }

}
