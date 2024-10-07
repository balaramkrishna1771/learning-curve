package com.reactivespring.studentspaymentservice.service;

import com.reactivespring.studentspaymentservice.domain.Payment;
import com.reactivespring.studentspaymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

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
                    payment.setPaymentId(updatedPaymentInfo.getPaymentId());
                    payment.setAmount(updatedPaymentInfo.getAmount());
                    payment.setStudentId(updatedPaymentInfo.getStudentId());
                    return paymentRepository.save(payment);
                });
    }

    public Mono<Void> deletePayment(Integer paymentId) {
        return paymentRepository.deleteById(paymentId);
    }

    public Flux<Payment> getPaymentsByStudentId(Integer studentId) {
        return paymentRepository.findAllByStudentId(studentId).log();
    }
}
