package com.example.auth.controller;

import com.example.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String mobile) {
        return ResponseEntity.ok(userService.registerUser(mobile));
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestParam String mobile, @RequestParam String otp) {
        return ResponseEntity.ok(userService.verifyOtp(mobile, otp));
    }
}
