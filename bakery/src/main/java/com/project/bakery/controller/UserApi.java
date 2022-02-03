package com.project.bakery.controller;

import com.project.bakery.business.UserBusiness;
import com.project.bakery.exception.BaseException;
import com.project.bakery.model.MRegisterLineRequest;
import com.project.bakery.model.MRegisterLineResponse;
import com.project.bakery.model.MRegisterRequest;
import com.project.bakery.model.MRegisterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    private final UserBusiness userBusiness;

    public UserApi(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @GetMapping
    public String Test() {
        return "two";
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<MRegisterResponse> register(@RequestBody MRegisterRequest request) throws BaseException {
        MRegisterResponse response = userBusiness.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @RequestMapping("/register/line")
    public ResponseEntity<MRegisterLineResponse> registerByLine(@RequestBody MRegisterLineRequest request) throws BaseException {
        MRegisterLineResponse response = userBusiness.registerByLine(request);
        return ResponseEntity.ok(response);
    }
}
