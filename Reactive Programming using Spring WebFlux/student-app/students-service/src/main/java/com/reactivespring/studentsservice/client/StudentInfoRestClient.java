package com.reactivespring.studentsservice.client;

import com.reactivespring.studentsservice.domain.StudentInfo;
import com.reactivespring.studentsservice.dto.StudentInfoDTO;
import com.reactivespring.studentsservice.exception.StudentInfoClientException;
import com.reactivespring.studentsservice.exception.StudentInfoServerException;
import com.reactivespring.studentsservice.util.RetryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Exceptions;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@Component
public class StudentInfoRestClient {

    @Value("${restClientUrl.studentInfoUrl}")
    private String studentInfoUrl;

    private WebClient webClient;
    public StudentInfoRestClient(WebClient webClient){
        this.webClient = webClient;
    }

    public Mono<StudentInfoDTO> retrieveStudentInfoById(Integer studentId){

        return webClient.get()
                .uri(studentInfoUrl.concat("/{id}"),studentId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    if(clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)){
                        return Mono.error(new StudentInfoClientException(
                                "There is no student with the provided id : "+studentId,clientResponse.statusCode().value()
                        ));
                    }
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(responseMessage -> {
                                return Mono.error(new StudentInfoClientException(responseMessage, clientResponse.statusCode().value()));
                            });
                })
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                    log.error("Error code : {}",clientResponse);
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(responseMessage -> Mono.error(new StudentInfoServerException(
                                    "Server exception"+responseMessage)));
                })
                .bodyToMono(StudentInfoDTO.class)
//                .retry(3)
                .retryWhen(RetryUtil.retrySpec())
                .log();
    }


}
