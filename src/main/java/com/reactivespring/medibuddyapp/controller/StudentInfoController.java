package com.reactivespring.medibuddyapp.controller;

import com.reactivespring.medibuddyapp.domain.StudentInfo;
import com.reactivespring.medibuddyapp.repository.StudentInfoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.reactivespring.medibuddyapp.service.StudentInfoService;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1")
@Slf4j
public class StudentInfoController {

    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private StudentInfoRepository studentInfoRepository;


    public StudentInfoController(StudentInfoService studentInfoService) {
        this.studentInfoService = studentInfoService;
    }

    @GetMapping("/studentInfos")
    public Flux<StudentInfo> sampleStudentInfo(@RequestParam(value = "age" , required = false) byte age){
        log.info("Searching with age : {}",age );
        if(age != 0 ){
            return Flux.from(studentInfoService.getStudentByAge(age)).log();
        }
        return studentInfoService.sampleStudentInfo().log();
    }

    @GetMapping("/studentInfos/{id}")
    public Mono<StudentInfo> studentInfoById(@PathVariable Long id){
        return studentInfoService.getStudentById(id).log();
    }

    @PostMapping("/studentInfos")
    @ResponseStatus(CREATED)
    public Mono<StudentInfo> addStudentInfo(@RequestBody @Valid StudentInfo studentInfo){
        return studentInfoService.addStudentInfo(studentInfo);
    }

    @PutMapping("studentInfos/{id}")
    public Mono<ResponseEntity<StudentInfo>> updateStudentInfo(@PathVariable Long id, @RequestBody StudentInfo updatedStudentInfo){
        return studentInfoService.updateStudentInfo(updatedStudentInfo,id)
                .map(movieInfo -> ResponseEntity.ok().body(movieInfo))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                .log();
    }

    @DeleteMapping("studentInfos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteStudentInfo(@PathVariable Long id){
        return studentInfoService.deleteStudentInfo(id);
    }
}
