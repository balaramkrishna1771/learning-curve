package com.reactivespring.studentspaymentservice.repository;

import com.reactivespring.studentspaymentservice.domain.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends ReactiveCrudRepository<Payment, Integer> {
}
