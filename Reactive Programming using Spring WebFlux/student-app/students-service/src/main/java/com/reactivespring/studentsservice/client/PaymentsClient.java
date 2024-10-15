package com.reactivespring.studentsservice.client;

import com.reactivespring.studentsservice.domain.Payment;
import com.reactivespring.studentsservice.dto.PaymentDTO;
import com.reactivespring.studentsservice.exception.PaymentsClientException;
import com.reactivespring.studentsservice.exception.PaymentsServerException;
import com.reactivespring.studentsservice.exception.StudentInfoServerException;
import com.reactivespring.studentsservice.util.RetryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
public class PaymentsClient {

    @Value("${restClientUrl.paymentsUrl}")
    private String paymentsUrl;

    private final WebClient webClient;
    public PaymentsClient(WebClient webClient){
        this.webClient = webClient;
    }


    public Flux<PaymentDTO> retrievePayments(Integer studentId){

        var url = UriComponentsBuilder.fromHttpUrl(paymentsUrl)
                .queryParam("studentId", studentId)
                .buildAndExpand().toUri();
        return webClient.get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    if(clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)){
                        return Mono.empty();
                    }
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(responseMessage -> {
                                return Mono.error(new PaymentsClientException(responseMessage,clientResponse.statusCode().value()));
                            });
                })
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                    log.error("Error code : {}",clientResponse.statusCode());
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(responseMessage -> Mono.error(new PaymentsServerException(
                                    "Server exception in Payments server"+responseMessage)));
                })
                .bodyToFlux(PaymentDTO.class)
                .retryWhen(RetryUtil.retrySpec())
                .log();
    }
}
