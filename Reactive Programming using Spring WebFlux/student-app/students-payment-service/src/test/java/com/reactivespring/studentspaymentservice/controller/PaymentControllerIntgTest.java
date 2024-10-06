package com.reactivespring.studentspaymentservice.controller;

import com.reactivespring.studentspaymentservice.domain.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class PaymentControllerIntgTest {

    private static String PAYMENT_URL = "/v1/payments";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getAllPayments() {
        webTestClient.get()
                .uri(PAYMENT_URL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Payment.class)
                .hasSize(12);
    }


    @Test
    void addPaymentRecord() {
        var paymentInfo = new Payment(2,320F,null);

        webTestClient.post()
                .uri(PAYMENT_URL)
                .bodyValue(paymentInfo)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void getPaymentById() {
        var paymentId = 1;

        webTestClient.get()
                .uri(PAYMENT_URL+"/{id}" , paymentId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Payment.class)
                .consumeWith(paymentEntityExchangeResult -> {
                    var result = paymentEntityExchangeResult.getResponseBody();
                    assertEquals(100.5F,result.getAmount());
                });
    }

    @Test
    void updatePaymentRecord() {
        var paymentInfo = new Payment(2,997F,13);
        var paymentId = 13;

        webTestClient.put()
                .uri(PAYMENT_URL+"/{id}",paymentId)
                .bodyValue(paymentInfo)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Payment.class)
                .consumeWith(paymentEntityExchangeResult -> {
                    var res = paymentEntityExchangeResult.getResponseBody();
                    assertEquals(997F,res.getAmount());
                });

    }

    @Test
    void deletePaymentInfo() {
        var paymentId = 13;

        webTestClient.delete()
                .uri(PAYMENT_URL+"/{id}", paymentId)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }
}