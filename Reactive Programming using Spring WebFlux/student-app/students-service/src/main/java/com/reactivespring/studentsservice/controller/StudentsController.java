package com.reactivespring.studentsservice.controller;

import com.reactivespring.studentsservice.client.PaymentsClient;
import com.reactivespring.studentsservice.client.StudentInfoRestClient;
import com.reactivespring.studentsservice.dto.StudentDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/student/{id}")
    public Mono<StudentDTO> getStudentInfo(@PathVariable("id") Integer studentId){
        return studentInfoRestClient.retrieveStudentInfoById(studentId)
                .flatMap(studentInfo -> {
                        var paymentsListMono = paymentsClient.retrievePayments(studentId)
                                .collectList();

                        return paymentsListMono.map(payments -> new StudentDTO(studentInfo,payments));
                });
    }
}
