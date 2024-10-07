package com.reactivespring.studentsservice.client;

import com.reactivespring.studentsservice.domain.Payment;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class PaymentsClient {

    @Value("${restClientUrl.paymentsUrl}")
    private String paymentsUrl;

    private WebClient webClient;
    public PaymentsClient(WebClient webClient){
        this.webClient = webClient;
    }


    public Flux<Payment> retrievePayments(Integer studentId){

        var url = UriComponentsBuilder.fromHttpUrl(paymentsUrl)
                .queryParam("studentId", studentId)
                .buildAndExpand().toUri();
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(Payment.class)
                .log();
    }
}
