package com.reactivespring.studentspaymentservice.service;

import com.reactivespring.studentspaymentservice.domain.Payment;
import com.reactivespring.studentspaymentservice.dto.PaymentDTO;
import com.reactivespring.studentspaymentservice.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final RabbitTemplate rabbitTemplate;

    private PaymentMapper paymentMapper = new PaymentMapper();

    public PaymentService(PaymentRepository paymentRepository, RabbitTemplate rabbitTemplate){
        this.paymentRepository = paymentRepository;
        this.rabbitTemplate = rabbitTemplate;
    }


    @RabbitListener(queues = "${rabbitmq.queue.paymentsQueue}")
    public void handlePaymentsRequest(Integer studentId){
        log.info("Request recieved for student info with id : {}",studentId);
        paymentRepository.findAllByStudentId(studentId).map(paymentMapper)
                .subscribe(paymentDTO -> {
                    log.info("Sending response from Payments {}",paymentDTO);
                    rabbitTemplate.convertAndSend("${rabbitmq.exchange.studentServiceExchange}","${rabbitmq.routingKeys.paymentResponseRoutingKey}",paymentDTO.toString());

                });


    }

    public Flux<PaymentDTO> getAllPayments(){
        return paymentRepository.findAll()
                .map(paymentMapper)
                .log();
    }

    public Mono<PaymentDTO> addPaymentRecord(Payment payment) {
        return paymentRepository.save(payment).map(paymentMapper).log();
    }

    public Mono<PaymentDTO> getPaymentById(Integer paymentId) {
        return paymentRepository.findById(paymentId)
                .map(paymentMapper).log();
    }

    public Mono<PaymentDTO> updatePaymentRecord(Payment updatedPaymentInfo, Integer paymentId) {
        return paymentRepository.findById(paymentId)
                .flatMap(payment -> {
                    payment.setPaymentId(updatedPaymentInfo.getPaymentId());
                    payment.setAmount(updatedPaymentInfo.getAmount());
                    payment.setStudentId(updatedPaymentInfo.getStudentId());
                    return paymentRepository.save(payment);
                }).map(paymentMapper);
    }

    public Mono<Void> deletePayment(Integer paymentId) {
        return paymentRepository.deleteById(paymentId);
    }

    public Flux<PaymentDTO> getPaymentsByStudentId(Integer studentId) {
        return paymentRepository.findAllByStudentId(studentId)
                .map(paymentMapper).log();
    }
}
