//package com.project.bakery.controller;
//
//import com.fasterxml.jackson.annotation.JsonRawValue;
//import com.fasterxml.jackson.annotation.JsonValue;
//import com.project.bakery.entity.User;
//import com.project.bakery.service.UserService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin
//@RestController()
//@RequestMapping("/api/users")
//public class UserController {
//
//    private UserService userService;
//
//    public UserController(UserService userService){
//        this.userService = userService;
//    }
//
//    @GetMapping("/allUsers")
//    public List<User> getAllUsers(){
//        return userService.getUsers();
//    }
//
//    @PostMapping("/signup")
//    public User createUser(@RequestBody User user){
//        userService.createUser(user);
//        return user;
//    }
//
//    @PostMapping("/login")
//    public Json get(){
////        return new Json("{ \"accessToken\" : \"1234\" }");
//
//        return  new Json("{\n" +
//                "    \"status\": \"ok\",\n" +
//                "    \"message\": \"Logged in\",\n" +
//                "    \"accessToken\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Imthcm4ueW9uZ0BtZWNhbGxhcGkuY29tIiwiaWF0IjoxNjQzMjc2NzA0fQ.ANO1jmwgM4auvu3EkEZ-9hNibWDIHkjlgKmm6UGT06Q\",\n" +
//                "    \"user\": {\n" +
//                "        \"id\": 1,\n" +
//                "        \"fname\": \"Karn\",\n" +
//                "        \"lname\": \"Yong\",\n" +
//                "        \"username\": \"karn.yong@mecallapi.com\",\n" +
//                "        \"email\": \"karn.yong@mecallapi.com\",\n" +
//                "        \"avatar\": \"https://www.mecallapi.com/users/1.png\"\n" +
//                "    }\n" +
//                "}");
//    }
//
//}
//
//class Json {
//    private final String value;
//    public Json(String value) {
//        this.value = value;
//    }
//    @JsonValue
//    @JsonRawValue
//    public String value() {
//        return value;
//    }
//}
