package com.reactivespring.studentsservice.controller;

import com.reactivespring.studentsservice.client.PaymentsClient;
import com.reactivespring.studentsservice.client.StudentInfoRestClient;
import com.reactivespring.studentsservice.dto.StudentDTO;
import com.reactivespring.studentsservice.service.RabbitMQProducer;
import com.reactivespring.studentsservice.service.SSEMessageListner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class StudentsController {

    private final StudentInfoRestClient studentInfoRestClient;
    private final PaymentsClient paymentsClient;

    public StudentsController(StudentInfoRestClient studentInfoRestClient, PaymentsClient paymentsClient){
        this.studentInfoRestClient = studentInfoRestClient;
        this.paymentsClient = paymentsClient;
    }

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @Autowired
    private SSEMessageListner sseMessageListner;

    @GetMapping("/student/{id}")
    public Mono<StudentDTO> getStudentInfo(@PathVariable("id") Integer studentId){
        return studentInfoRestClient.retrieveStudentInfoById(studentId)
                .flatMap(studentInfo -> {
                        var paymentsListMono = paymentsClient.retrievePayments(studentId)
                                .collectList();

                        return paymentsListMono.map(payments -> new StudentDTO(studentInfo,payments));
                });
    }

    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<StudentDTO> streamMessages(@RequestParam Integer studentId) {
        rabbitMQProducer.requestStudentInfo(studentId);
        rabbitMQProducer.requestPayments(studentId);
        return sseMessageListner.subscribeMessages();
    }
}
