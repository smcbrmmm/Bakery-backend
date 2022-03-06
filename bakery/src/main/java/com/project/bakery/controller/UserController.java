package com.project.bakery.controller;

import com.project.bakery.business.UserBusiness;
import com.project.bakery.exception.BaseException;
import com.project.bakery.model.*;
import com.project.bakery.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        response.setAccessToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Imthcm4ueW9uZ0BtZWNhbGxhcGkuY29tIiwiaWF0IjoxNjQzMjc2NzA0fQ.ANO1jmwgM4auvu3EkEZ-9hNibWDIHkjlgKmm6UGT06Q");
        response.setMessage("Logged in");
        return response;
    }

//    @PostMapping
//    @RequestMapping("/register")
//    public ResponseEntity<MRegisterResponse> register(@RequestBody MRegisterRequest request) throws BaseException {
//        MRegisterResponse response = userBusiness.register(request);
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping
//    @RequestMapping("/register/line")
//    public ResponseEntity<MRegisterLineResponse> registerByLine(@RequestBody MRegisterLineRequest request) throws BaseException {
//        MRegisterLineResponse response = userBusiness.registerByLine(request);
//        return ResponseEntity.ok(response);
//    }
}
