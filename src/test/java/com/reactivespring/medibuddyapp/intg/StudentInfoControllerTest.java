package com.reactivespring.medibuddyapp.intg;

import com.reactivespring.medibuddyapp.MediBuddyAppApplication;
import com.reactivespring.medibuddyapp.domain.StudentInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.reactivespring.medibuddyapp.repository.StudentInfoRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(classes = MediBuddyAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")

public class StudentInfoControllerTest {

    private static String STUDENT_INFO_URL = "/v1/studentInfos";
    @Autowired
    StudentInfoRepository studentInfoRepository;

    @Autowired
    WebTestClient webTestClient;

//    @BeforeEach
//    void setUp() {
//
//        var students = List.of(
//                new StudentInfo(12L, "Alice Johnson", "alice.johnson@example.com", (byte) 22, "Female", "alicej", "P@ssw0rd123"),
//                new StudentInfo(13L, "Bob Smith", "bob.smith@example.com", (byte) 25, "Male", "bobsmith", "B0b$3cr3t!"),
//                new StudentInfo(31L, "Charlie Brown", "charlie.brown@example.com", (byte) 20, "Male", "charlieb", "C!h@rli3#2024"),
//                new StudentInfo(42L, "Diana Prince", "diana.prince@example.com", (byte) 23, "Female", "dianapr", "D!@n@2024#")
//        );
//
//        studentInfoRepository.saveAll(students).blockLast();
//
//    }

//    @AfterEach
//    void tearDown() {
//        studentInfoRepository.deleteAll().block();
//    }

    @Test
    void sampleStudentInfo() {

        webTestClient.get()
                .uri("")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(String.class)
                .consumeWith(EntityExchangeResult -> {
                    var studentInfo = EntityExchangeResult.getResponseBody();
                    assert studentInfo != null;

                    System.out.println(studentInfo);
                });
    }
    @Test
    void sampleStudentInfo_age() {
        var age = 25;

        var uri = UriComponentsBuilder.fromUriString(STUDENT_INFO_URL)
                        .queryParam("age",age).buildAndExpand().toUri();

        webTestClient.get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(StudentInfo.class)
                .consumeWith(EntityExchangeResult -> {
                    var studentInfo = EntityExchangeResult.getResponseBody();
                    assert studentInfo != null;
                    assertEquals("Male",studentInfo.get(0).getGender());
                });
    }

    @Test
    void studentInfoById() {

        var id = 12;
        webTestClient.get()
                .uri(STUDENT_INFO_URL+"/{id}",id)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(String.class)
                .consumeWith(EntityExchangeResult -> {
                    var studentInfo = EntityExchangeResult.getResponseBody();
                    assert studentInfo != null;
                    System.out.println(studentInfo);
                });
    }

    @Test
    void addStudentInfo() {
        var newStudentInfo =  new StudentInfo(null, "Bob Smith", "bob.smith@example.com", (byte) 25, "Male", "bobsmith", "B0b$3cr3t!");

        webTestClient.post()
                .uri(STUDENT_INFO_URL)
                .bodyValue(newStudentInfo)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(StudentInfo.class)
                .consumeWith(EntityExchangeResult -> {
                    var studentInfo = EntityExchangeResult.getResponseBody();
                    assert studentInfo != null;
                    assertEquals("Bob Smith", studentInfo.getName());
                });
    }

    @Test
    void updateStudentInfo() {
        var newStudentInfo = new StudentInfo(48L, "Steve Smith", "bob.smith@example.com",
                (byte) 25, "Male", "bobsmith", "B0b$3cr3t!");

        webTestClient.put()
                .uri(STUDENT_INFO_URL+"/{id}",newStudentInfo.getId())
                .bodyValue(newStudentInfo)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(StudentInfo.class)
                .consumeWith(EntityExchangeResult -> {
                    var studentInfo = EntityExchangeResult.getResponseBody();
                    assert studentInfo != null;
                    assertEquals("Steve Smith", studentInfo.getName());
                });
    }

    @Test
    void deleteStudentInfo() {

        var id = 12;
        webTestClient.delete()
                .uri(STUDENT_INFO_URL+"/{id}",id)
                .exchange()
                .expectStatus()
                .isNoContent()
                .expectBody(Void.class);
    }

    @Test
    void addStudentInfo_validation() {
        var newStudentInfo =  new StudentInfo(null, "", "bob.smith@example.com", (byte) -12, "Male", "bobsmith", "B0b$3cr3t!");

        webTestClient.post()
                .uri(STUDENT_INFO_URL)
                .bodyValue(newStudentInfo)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody(String.class)
                .consumeWith(EntityExchangeResult -> {
                    var studentInfo = EntityExchangeResult.getResponseBody();
                    assert studentInfo != null;
                    assertEquals("Age must be positive,Name mush be present",studentInfo);
                });
//                .expectBody(StudentInfo.class)
//                .consumeWith(EntityExchangeResult -> {
//                    var studentInfo = EntityExchangeResult.getResponseBody();
//                    assert studentInfo != null;
//                    assertEquals("Bob Smith", studentInfo.getName());
//                });



    }

    @Test
    void updateStudentInfo_notfound() {
        var newStudentInfo = new StudentInfo(null, "Steve Smith", "bob.smith@example.com",
                (byte) 25, "Male", "bobsmith", "B0b$3cr3t!");

        webTestClient.put()
                .uri(STUDENT_INFO_URL+"/{id}",12L)
                .bodyValue(newStudentInfo)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

}