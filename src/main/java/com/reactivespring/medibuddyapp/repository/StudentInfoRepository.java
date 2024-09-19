package com.reactivespring.medibuddyapp.repository;

import com.reactivespring.medibuddyapp.domain.StudentInfo;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface StudentInfoRepository extends ReactiveCrudRepository<StudentInfo, Long> {
    Mono<StudentInfo> findByName(String name);
    Mono<StudentInfo> findStudentByAge(byte age);
}
