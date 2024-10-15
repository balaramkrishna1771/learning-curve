package com.reactivespring.studentsinfoservice.controller;

import com.reactivespring.studentsinfoservice.domain.StudentInfo;
import com.reactivespring.studentsinfoservice.dto.StudentInfoDTO;
import com.reactivespring.studentsinfoservice.service.StudentInfoMapper;
import com.reactivespring.studentsinfoservice.service.StudentsInfoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.w3c.dom.stylesheets.LinkStyle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@WebFluxTest(StudentsInfoController.class)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class StudentsInfoControllerUnitTest {

    private static String STUDENT_INFO_URL = "/v1/studentinfos";

    @MockBean
    private StudentsInfoService studentsInfoServiceMock;

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private StudentInfoMapper studentInfoMapper;


    @Test
    void getStudentsInfo() {
        var studentInfos = List.of(new StudentInfoDTO("ram krishna",25),
                new StudentInfoDTO("Steve smith",26));

        when(studentsInfoServiceMock.findAllStudentsInfo()).thenReturn(Flux.fromIterable(studentInfos));

        webTestClient.get()
                .uri(STUDENT_INFO_URL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(StudentInfo.class)
                .hasSize(2);
    }

    @Test
    void getStudentInfoById() {
        var studentId = 3;
        var studentInfo = new StudentInfoDTO("ram krishna",25);
        when(studentsInfoServiceMock.findStudentById(studentId)).thenReturn(Mono.just(studentInfo));

        webTestClient.get()
                .uri(STUDENT_INFO_URL + "/{id}",studentId)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(StudentInfo.class)
                .consumeWith(studentInfoEntityExchangeResult -> {
                    var result = studentInfoEntityExchangeResult.getResponseBody();
                    assertNotNull(result);
                    System.out.println(result);
                    assertEquals("ram.krishna@gmail.com", result.getEmail());
                });

    }

    @Test
    void addStudentInfo() {
        var studentInfo = new StudentInfo(7,"ram","krishna","ram.krishna@gmail.com",25);

        var studentInfoDTO = new StudentInfoDTO("ram krishna",25);

        when(studentsInfoServiceMock.addStudentInfo(studentInfo)).thenReturn(Mono.just(studentInfoDTO));

        webTestClient.post()
                .uri(STUDENT_INFO_URL)
                .bodyValue(studentInfo)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(String.class);
    }

    @Test
    void updateStudentInfo() {
        var updatedStudentInfo = new StudentInfo(6,"Balaram","krishna","ram.krishna@gmail.com",27);
        var studentId = 6;

        when(studentsInfoServiceMock.updatedStudentInfo(updatedStudentInfo,studentId))
                .thenReturn(Mono.just(updatedStudentInfo).map(studentInfoMapper));

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

        when(studentsInfoServiceMock.deleteStudentInfo(studentId)).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri(STUDENT_INFO_URL+"/{id}", studentId)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Void.class);

    }
}