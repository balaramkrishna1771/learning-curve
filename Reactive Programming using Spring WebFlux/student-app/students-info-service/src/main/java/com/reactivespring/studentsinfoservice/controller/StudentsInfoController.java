package com.reactivespring.studentsinfoservice.controller;

import com.reactivespring.studentsinfoservice.domain.StudentInfo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import com.reactivespring.studentsinfoservice.service.StudentsInfoService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class StudentsInfoController {

    private final StudentsInfoService studentsInfoService;


    public StudentsInfoController(StudentsInfoService studentsInfoService){
        this.studentsInfoService = studentsInfoService;
    }

    @GetMapping("/studentinfos")
    public Flux<StudentInfo> getStudentsInfo(@RequestParam(value = "email", required = false) String email){
        if(email != null){
            return studentsInfoService.findStudentInfoByEmail(email);
        }
        return studentsInfoService.findAllStudentsInfo();
    }

    @GetMapping("/studentinfos/{id}")
    public Mono<ResponseEntity<StudentInfo>> getStudentInfoById(@PathVariable("id") Integer studentId){
        return studentsInfoService.findStudentById(studentId)
                .map(studentInfo -> ResponseEntity.ok().body(studentInfo))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @PostMapping("/studentinfos")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<StudentInfo> addStudentInfo(@RequestBody @Valid StudentInfo studentInfo){
        return studentsInfoService.addStudentInfo(studentInfo);
    }

    @PutMapping("/studentinfos/{id}")
    public Mono<StudentInfo> updateStudentInfo(@RequestBody StudentInfo updatedStudentInfo, @PathVariable("id") Integer studentId){
        return studentsInfoService.updatedStudentInfo(updatedStudentInfo, studentId);
    }

    @DeleteMapping("/studentinfos/{id}")
    public Mono<Void> deleteStudentInfo(@PathVariable("id") Integer studentId){
        return studentsInfoService.deleteStudentInfo(studentId);
    }
}
