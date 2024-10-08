package com.reactivespring.studentsservice.controller;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.reactivespring.studentsservice.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.Exceptions;
import reactor.util.retry.Retry;

import java.time.Duration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@AutoConfigureWireMock(port = 8884)
@TestPropertySource(
        properties = {
                "restClientUrl.studentInfoUrl=http://localhost:8884/v1/studentinfos",
                "restClientUrl.paymentsUrl=http://localhost:8884/v1/payments"
        }
)
class StudentsControllerIntgTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getStudentInfo() {
        var studentId = 2;

        stubFor(get(urlEqualTo("/v1/studentinfos"+"/"+studentId))
                .willReturn(aResponse()
                        .withHeader("Content-Type","application/json")
                        .withBodyFile("studentinfo.json")));

        stubFor(get(urlPathEqualTo("/v1/payments"))
                .willReturn(aResponse()
                        .withHeader("Content-Type","application/json")
                        .withBodyFile("payments.json")));

        webTestClient.get()
                .uri("/v1/student/{id}", studentId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Student.class)
                .consumeWith(studentEntityExchangeResult -> {
                    var res = studentEntityExchangeResult.getResponseBody();
                    assertNotNull(res);
                    assertEquals("Jane",res.getStudentInfo().getFirst_name());
                });

    }
    @Test
    void getStudentInfo_404() {
        var studentId = 2;

        stubFor(get(urlEqualTo("/v1/studentinfos"+"/"+studentId))
                .willReturn(aResponse()
                        .withStatus(404)));

        stubFor(get(urlPathEqualTo("/v1/payments"))
                .willReturn(aResponse()
                        .withHeader("Content-Type","application/json")
                        .withBodyFile("payments.json")));

        webTestClient.get()
                .uri("/v1/student/{id}", studentId)
                .exchange()
                .expectStatus()
                .is4xxClientError();

        WireMock.verify(1,getRequestedFor(urlEqualTo("/v1/studentinfos"+"/"+studentId)));


    }
    @Test
    void getStudentInfo_payments_404() {
        var studentId = 2;

        stubFor(get(urlEqualTo("/v1/studentinfos"+"/"+studentId))
                .willReturn(aResponse()
                        .withHeader("Content-Type","application/json")
                        .withBodyFile("studentinfo.json")));

        stubFor(get(urlPathEqualTo("/v1/payments"))
                .willReturn(aResponse()
                        .withStatus(404)));

        webTestClient.get()
                .uri("/v1/student/{id}", studentId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Student.class)
                .consumeWith(studentEntityExchangeResult -> {
                    var res = studentEntityExchangeResult.getResponseBody();
                    assertNotNull(res);
                    assertEquals(0,res.getPayments().size());
                    assertEquals("Jane",res.getStudentInfo().getFirst_name());
                });

    }
    @Test
    void getStudentInfo_5XX() {
        var studentId = 2;

        stubFor(get(urlEqualTo("/v1/studentinfos"+"/"+studentId))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withBody("Student Info Server is Unavailable")));



        webTestClient.get()
                .uri("/v1/student/{id}", studentId)
                .exchange()
                .expectStatus()
                .is5xxServerError()
                .expectBody(String.class)
                .isEqualTo("Server exceptionStudent Info Server is Unavailable");

        WireMock.verify(4,getRequestedFor(urlEqualTo("/v1/studentinfos"+"/"+studentId)));

    }

    @Test
    void getStudentInfo_payments_5XX() {
        var studentId = 2;

        stubFor(get(urlEqualTo("/v1/studentinfos"+"/"+studentId))
                .willReturn(aResponse()
                        .withHeader("Content-Type","application/json")
                        .withBodyFile("studentinfo.json")));

        stubFor(get(urlPathEqualTo("/v1/payments"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withBody("Payments Service is not available")));



        webTestClient.get()
                .uri("/v1/student/{id}", studentId)
                .exchange()
                .expectStatus()
                .is5xxServerError()
                .expectBody(String.class)
                .isEqualTo("Server exception in Payments serverPayments Service is not available");

        WireMock.verify(4,getRequestedFor(urlPathMatching("/v1/payments*")));

    }

}