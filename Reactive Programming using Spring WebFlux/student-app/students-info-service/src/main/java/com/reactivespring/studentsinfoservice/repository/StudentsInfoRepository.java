package com.reactivespring.studentsinfoservice.repository;

import com.reactivespring.studentsinfoservice.domain.StudentInfo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StudentsInfoRepository extends ReactiveCrudRepository<StudentInfo, Integer> {
    public Flux<StudentInfo> findByEmail(String email);
}
