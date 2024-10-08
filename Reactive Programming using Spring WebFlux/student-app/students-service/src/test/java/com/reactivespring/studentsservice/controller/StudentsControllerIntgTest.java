package com.reactivespring.studentsservice.controller;

import com.reactivespring.studentsservice.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class StudentsControllerIntgTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getStudentInfo() {
        var studentId = 2;

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
}