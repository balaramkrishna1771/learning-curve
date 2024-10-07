package com.reactivespring.studentsservice.controller;

import com.reactivespring.studentsservice.client.PaymentsClient;
import com.reactivespring.studentsservice.client.StudentInfoRestClient;
import com.reactivespring.studentsservice.domain.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class StudentsController {

    private StudentInfoRestClient studentInfoRestClient;
    private PaymentsClient paymentsClient;

    public StudentsController(StudentInfoRestClient studentInfoRestClient, PaymentsClient paymentsClient){
        this.studentInfoRestClient = studentInfoRestClient;
        this.paymentsClient = paymentsClient;
    }

    @GetMapping("/student")
    public Mono<Student> getStudentInfo(){
        var studentId = 2;
        return studentInfoRestClient.retrieveStudentInfoById(studentId)
                .flatMap(studentInfo -> {
                        var paymentsListMono = paymentsClient.retrievePayments(studentId)
                                .collectList();

                        return paymentsListMono.map(payments -> new Student(studentInfo,payments));
                });
    }
}
