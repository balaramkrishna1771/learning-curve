package com.reactivespring.medibuddyapp.unit;

import com.reactivespring.medibuddyapp.MediBuddyAppApplication;
import com.reactivespring.medibuddyapp.domain.StudentInfo;
import com.reactivespring.medibuddyapp.service.StudentInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = MediBuddyAppApplication.class)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class StudentInfoControllerTest {

    @MockBean
    private StudentInfoService studentInfoServiceMock;
    @Autowired
    private WebTestClient webClient;



    @Test
    void sampleStudentInfo() {
        var students = List.of(
                new StudentInfo(1L, "Alice Johnson", "alice.johnson@example.com", (byte) 22, "Female", "alicej", "P@ssw0rd123"),
                new StudentInfo(2L, "Bob Smith", "bob.smith@example.com", (byte) 25, "Male", "bobsmith", "B0b$3cr3t!"),
                new StudentInfo(3L, "Charlie Brown", "charlie.brown@example.com", (byte) 20, "Male", "charlieb", "C!h@rli3#2024"),
                new StudentInfo(4L, "Diana Prince", "diana.prince@example.com", (byte) 23, "Female", "dianapr", "D!@n@2024#")
        );


        when(studentInfoServiceMock.sampleStudentInfo()).thenReturn(Flux.fromIterable(students));

        webClient.get()
                .uri("/v1/studentInfos")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(StudentInfo.class)
                .hasSize(4);
//                .consumeWith(EntityExchangeResult -> {
//                    var studentInfo = EntityExchangeResult.getResponseBody();
//                    assert studentInfo != null;
//
//                });
    }
}