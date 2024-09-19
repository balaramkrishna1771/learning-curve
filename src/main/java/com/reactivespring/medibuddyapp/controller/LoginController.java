package com.reactivespring.medibuddyapp.controller;

import com.reactivespring.medibuddyapp.domain.Login;
import com.reactivespring.medibuddyapp.service.LoginService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody Login loginRequest) {
        log.info("Login request: {}", loginRequest);
        return loginService.authenticateLogin(loginRequest)
                .map(isAuthenticated ->
                        ResponseEntity.ok().body(isAuthenticated ? "Authenticated" : "Not Authenticated"));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/signup")
    public Mono<ResponseEntity<Login>> signUp(@RequestBody Login signupRequest) {
        log.info("Sign Up request: {}", signupRequest);
        return loginService.createNewUser(signupRequest)
                .map(loginResponse -> ResponseEntity.ok().body(loginResponse));
    }
}
