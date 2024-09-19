package com.reactivespring.medibuddyapp.intg;


import com.reactivespring.medibuddyapp.domain.Login;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class LoginControllerIntgTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testLogin() {

        var userDetails = new Login(null,"bob_miller","pass12345","Bob","Miller");

        webTestClient.post()
                .uri("/v1/login")
                .bodyValue(userDetails)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    var resp = response.getResponseBody();
                    assert resp != null;
                    System.out.println(resp);
                });
    }

    @Test
    void signUp() {

        var userDetails = new Login(null,"balaram@gmail.com","pass12345","Balaram","Krishna");

        webTestClient.post()
                .uri("/v1/signup")
                .bodyValue(userDetails)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Login.class)
                .consumeWith(response -> {
                    var resp = response.getResponseBody();
                    assert resp != null;
                    System.out.println(resp);
                });
    }
}
