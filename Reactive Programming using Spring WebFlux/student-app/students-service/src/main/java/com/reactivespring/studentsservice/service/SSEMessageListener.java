package com.reactivespring.studentsservice.service;

import com.reactivespring.studentsservice.dto.StudentDTO;
import com.reactivespring.studentsservice.dto.StudentInfoDTO;
import com.reactivespring.studentsservice.dto.PaymentDTO;  // Assuming you have a PaymentDTO for payment data
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class SSEMessageListener {

    private final Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();

    // Temporary store to hold incomplete student data until both responses are received
    private final ConcurrentHashMap<String, StudentDTO> studentDataStore = new ConcurrentHashMap<>();

    public Flux<String> subscribeMessages() {
        return sink.asFlux();
    }

    public void emitMessage(String message) {
        sink.tryEmitNext(message);
    }

    // Listener for student info service response
    @RabbitListener(queues = "${rabbitmq.queue.infoResponseQueue}")
    public void receiveStudentInfoData(String studentInfoDTO) {
        log.info("Received student info: {}", studentInfoDTO);
        emitMessage(studentInfoDTO);

    }

    // Listener for payment service response
    @RabbitListener(queues = "${rabbitmq.queue.payemtsResponseQueue}")
    public void receivePaymentData(String paymentDTO) {
        log.info("Received payment info: {}", paymentDTO);
        emitMessage(paymentDTO);

    }
}
