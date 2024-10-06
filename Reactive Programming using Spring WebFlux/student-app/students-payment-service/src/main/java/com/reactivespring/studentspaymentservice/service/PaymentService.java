package com.reactivespring.studentspaymentservice.service;

import com.reactivespring.studentspaymentservice.domain.Payment;
import com.reactivespring.studentspaymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    public Flux<Payment> getAllPayments(){
        return paymentRepository.findAll().log();
    }

    public Mono<Payment> addPaymentRecord(Payment payment) {
        return paymentRepository.save(payment).log();
    }

    public Mono<Payment> getPaymentById(Integer paymentId) {
        return paymentRepository.findById(paymentId).log();
    }

    public Mono<Payment> updatePaymentRecord(Payment updatedPaymentInfo, Integer paymentId) {
        return paymentRepository.findById(paymentId)
                .flatMap(payment -> {
                    payment.setPayment_id(updatedPaymentInfo.getPayment_id());
                    payment.setAmount(updatedPaymentInfo.getAmount());
                    payment.setStudent_id(updatedPaymentInfo.getStudent_id());
                    return paymentRepository.save(payment);
                });
    }

    public Mono<Void> deletePayment(Integer paymentId) {
        return paymentRepository.deleteById(paymentId);
    }
}
