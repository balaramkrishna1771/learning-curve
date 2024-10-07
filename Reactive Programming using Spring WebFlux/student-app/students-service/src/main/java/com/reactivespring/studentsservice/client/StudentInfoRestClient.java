package com.reactivespring.studentsservice.client;

import com.reactivespring.studentsservice.domain.StudentInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class StudentInfoRestClient {

    @Value("${restClientUrl.studentInfoUrl}")
    private String studentInfoUrl;

    private WebClient webClient;
    public StudentInfoRestClient(WebClient webClient){
        this.webClient = webClient;
    }

    public Mono<StudentInfo> retrieveStudentInfoById(Integer studentId){
        return webClient.get()
                .uri(studentInfoUrl.concat("/{id}"),studentId)
                .retrieve()
                .bodyToMono(StudentInfo.class)
                .log();
    }


}
