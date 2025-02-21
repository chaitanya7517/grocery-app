package com.example.user_service.controllers;


import com.example.user_service.services.UserService;
import com.example.user_service.utilities.AuthHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthHelper authHelper;

    @PostMapping("/register")
    public String saveUser(@RequestBody HashMap<String,String> reqDTO) {
        return userService.saveUser(reqDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody HashMap<String,String> reqDTO) {
        return userService.login(reqDTO);
    }

    @DeleteMapping("/logout")
    public String login(@RequestHeader("x-api-key") String apiKey) {
        return userService.logout(apiKey);
    }

    @GetMapping("/authenticate")
    public ResponseEntity<Boolean> authenticate(@RequestHeader("apiKey") String apiKey) {
        boolean isAuthenticated = authHelper.isAuthenticated(apiKey);
        return ResponseEntity.ok(isAuthenticated);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
