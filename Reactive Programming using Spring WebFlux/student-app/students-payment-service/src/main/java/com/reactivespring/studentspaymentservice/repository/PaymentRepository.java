package com.reactivespring.studentspaymentservice.repository;

import com.reactivespring.studentspaymentservice.domain.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PaymentRepository extends ReactiveCrudRepository<Payment, Integer> {
    Flux<Payment> findAllByStudentId(Integer studentId);
}
