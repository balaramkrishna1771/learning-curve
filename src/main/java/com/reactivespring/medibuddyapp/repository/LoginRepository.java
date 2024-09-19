package com.reactivespring.medibuddyapp.repository;


import com.reactivespring.medibuddyapp.domain.Login;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface LoginRepository extends R2dbcRepository<Login, String> {

    Mono<Login> findByUsername(String username);

}
