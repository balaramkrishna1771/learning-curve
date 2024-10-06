package com.reactivespring.moviesinfoservice.controller;

import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;


@WebFluxTest(controllers = FluxAndMonoController.class)
@AutoConfigureWebTestClient
class FluxAndMonoControllerTest{

    @Autowired
    private WebTestClient webTestClient;



    @Test
    void helloWorldMono(){

        webTestClient.get()
                .uri("/mono")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .consumeWith(stringEntityExchangeResult -> {
                    var response = stringEntityExchangeResult.getResponseBody();
                    assertNotNull(response);
                    assertEquals("Hello World", response);
                });


    }
    @Test
    void helloWorldMono_approach2(){

        var mono = webTestClient.get()
                .uri("/mono")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(String.class)
                .getResponseBody();

        StepVerifier.create(mono)
                .expectNext("Hello World")
                .verifyComplete();


    }


    @Test
    void flux() {

        var result = webTestClient.get()
                .uri("/flux")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(result)
                .expectNext(1,2,3,4,5,6,7)
                .verifyComplete();
    }

    @Test
    void stream() {

        var result = webTestClient.get()
                .uri("/stream")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Long.class)
                .getResponseBody();

        StepVerifier.create(result)
                .expectNext(0L,1L,2L)
                .thenCancel()
                .verify();
    }
}



//@WebFluxTest(controllers = FluxAndMonoController.class)
//@AutoConfigureWebTestClient
//class FluxAndMonoControllerTest {
//    @Autowired
//    WebTestClient webTestClient;
//
//    @Test
//    void flux() {
//        webTestClient
//                .get()
//                .uri("/flux")
//                .exchange()
//                .expectStatus()
//                .isOk()
//                .expectBodyList(Integer.class)
//                .hasSize(3);
//    }
//
//    @Test
//    void flux_approach2(){
//        var flux = webTestClient
//                .get()
//                .uri("/flux")
//                .exchange()
//                .expectStatus()
//                .isOk()
//                .returnResult(Integer.class)
//                .getResponseBody();
//
//        StepVerifier.create(flux)
//                .expectNext(1,2,3)
//                .verifyComplete();
//    }
//
//    @Test
//    void flux_approach3() {
//        webTestClient
//                .get()
//                .uri("/flux")
//                .exchange()
//                .expectStatus()
//                .isOk()
//                .expectBodyList(Integer.class)
//                .consumeWith(listEntityExchangeResult -> {
//                    var responseBody = listEntityExchangeResult.getResponseBody();
//                    assert (responseBody.size() == 3);
//                });
//    }
//
//    @Test
//    void mono() {
//        var flux = webTestClient.get()
//                .uri("/mono")
//                .exchange()
//                .expectStatus()
//                .isOk()
//                .returnResult(String.class)
//                .getResponseBody();
//        StepVerifier.create(flux)
//                .expectNext("mono")
//                .verifyComplete();
//    }
//    @Test
//    void mono_approach() {
//        var flux = webTestClient.get()
//                .uri("/mono")
//                .exchange()
//                .expectStatus()
//                .isOk()
//                .expectBodyList(String.class)
//                .consumeWith(stringEntityExchangeResult -> {
//                    var responseBody = stringEntityExchangeResult.getResponseBody();
//                    assertEquals("mono", responseBody);
//                });
//    }
//    @Test
//    void stream_approach2(){
//        var flux = webTestClient
//                .get()
//                .uri("/flux")
//                .exchange()
//                .expectStatus()
//                .isOk()
//                .returnResult(Long.class)
//                .getResponseBody();
//
//        StepVerifier.create(flux)
//                .expectNext(1L,2L,3L)
//                .thenCancel()
//                .verify();
////                .verifyComplete();
//    }
//
//    @Test
//    void helloWorldMono() {
//    }
//}