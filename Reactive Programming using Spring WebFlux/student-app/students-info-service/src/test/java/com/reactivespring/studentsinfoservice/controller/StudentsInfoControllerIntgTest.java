package com.reactivespring.studentsinfoservice.controller;

import com.reactivespring.studentsinfoservice.domain.StudentInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityResultHandler;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.test.StepVerifier;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class StudentsInfoControllerIntgTest {

    private static String STUDENT_INFO_URL = "/v1/studentinfos";

    @Autowired
    private WebTestClient webTestClient;


//
//    public StudentsInfoControllerIntgTest(WebTestClient webTestClient){
//        this.webTestClient = webTestClient;
//    }

    @Test
    void getStudentsInfo() {

        webTestClient.get()
                .uri(STUDENT_INFO_URL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(StudentInfo.class)
                .hasSize(5);
    }
    @Test
    void getStudentsInfo_byEmail() {
        var email = "bob.brown@example.com";
        var url = UriComponentsBuilder.fromUriString(STUDENT_INFO_URL)
                        .queryParam("email",email).buildAndExpand()
                        .toUri();
        webTestClient.get()
                .uri(url)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(StudentInfo.class)
                .hasSize(1);
    }

    @Test
    void getStudentInfoById() {
        var studentId = 3;
        webTestClient.get()
                .uri(STUDENT_INFO_URL + "/{id}",studentId)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(StudentInfo.class)
                .consumeWith(studentInfoEntityExchangeResult -> {
                    var result = studentInfoEntityExchangeResult.getResponseBody();
                    assertNotNull(result);
                    assertEquals("alice.johnson@example.com", result.getEmail());
                });

    }

    @Test
    void addStudentInfo() {
        var studentInfo = new StudentInfo(null,"ram","krishna","ram.krishna@gmail.com",25);

        webTestClient.post()
                .uri(STUDENT_INFO_URL)
                .bodyValue(studentInfo)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void addStudentInfo_validation() {
        var studentInfo = new StudentInfo(7,"ram","krishna","ram.krishna@gmail.com",-25);

        webTestClient.post()
                .uri(STUDENT_INFO_URL)
                .bodyValue(studentInfo)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody(String.class)
                .consumeWith(stringEntityExchangeResult -> {
                    var mes = stringEntityExchangeResult.getResponseBody();
                    assertEquals("studentInfo.age need to be a positive number",mes);
                });
    }

    @Test
    void updateStudentInfo() {
        var updatedStudentInfo = new StudentInfo(6,"Balaram","krishna","ram.krishna@gmail.com",27);
        var studentId = 6;

        webTestClient.put()
                .uri(STUDENT_INFO_URL+"/{id}", studentId)
                .bodyValue(updatedStudentInfo)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(StudentInfo.class)
                .consumeWith(studentInfoEntityExchangeResult -> {
                    var result = studentInfoEntityExchangeResult.getResponseBody();
                    assertEquals("Balaram",updatedStudentInfo.getFirst_name());
                    assertEquals(27,updatedStudentInfo.getAge());
                });
    }

    @Test
    void deleteStudentInfo() {
        var studentId = 6;

        webTestClient.delete()
                .uri(STUDENT_INFO_URL+"/{id}", studentId)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Void.class);
    }
    @Test
    void getStudentInfoById_404_not_found() {
        var studentId = 7;
        webTestClient.get()
                .uri(STUDENT_INFO_URL + "/{id}",studentId)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(String.class)
                .consumeWith(stringEntityExchangeResult -> {
                    System.out.println(stringEntityExchangeResult.getResponseBody());
                });

    }
}