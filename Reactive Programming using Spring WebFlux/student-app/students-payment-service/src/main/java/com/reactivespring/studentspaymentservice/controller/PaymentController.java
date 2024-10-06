package com.reactivespring.studentspaymentservice.controller;

import com.reactivespring.studentspaymentservice.domain.Payment;
import com.reactivespring.studentspaymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments")
    public Flux<Payment> getAllPayments(){
        return paymentService.getAllPayments();
    }

    @GetMapping("/payments/{id}")
    public Mono<ResponseEntity<Payment>> getPaymentById(@PathVariable("id") Integer paymentId){
        return paymentService.getPaymentById(paymentId)
                .map(paymentInfo -> ResponseEntity.status(HttpStatus.OK).body(paymentInfo))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PostMapping("/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Payment> addPaymentRecord(@RequestBody Payment payment){
        return paymentService.addPaymentRecord(payment);
    }

    @PutMapping("/payments/{id}")
    public Mono<Payment> updatePaymentRecord(@RequestBody Payment paymentInfo, @PathVariable("id") Integer paymentId){
        return paymentService.updatePaymentRecord(paymentInfo,paymentId);
    }

    @DeleteMapping("/payments/{id}")
    public Mono<Void> deletePaymentInfo(@PathVariable("id") Integer paymentId){
        return paymentService.deletePayment(paymentId);
    }

}
