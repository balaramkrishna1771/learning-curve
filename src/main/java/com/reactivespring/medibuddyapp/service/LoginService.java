package com.reactivespring.medibuddyapp.service;

import com.reactivespring.medibuddyapp.domain.Login;
import com.reactivespring.medibuddyapp.repository.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@Slf4j
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public Mono<Boolean> authenticateLogin(Login loginRequest) {
//        log.info("Authenticating login {}", loginRequest.getUsername());
        return loginRepository.findByUsername(loginRequest.getUsername())
                .map(login -> {
                    log.info("logged in: {}", login);
                    log.info("password: {}",login.getPassword().equals(loginRequest.getPassword()));
                    return login.getPassword().equals(loginRequest.getPassword());
                });
    }

    public Mono<Login> createNewUser(Login signupRequest) {
        return loginRepository.save(signupRequest).log();
    }
}
