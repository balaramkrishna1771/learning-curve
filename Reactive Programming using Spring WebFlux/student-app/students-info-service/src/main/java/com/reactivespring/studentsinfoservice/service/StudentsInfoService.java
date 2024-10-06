package com.reactivespring.studentsinfoservice.service;

import com.reactivespring.studentsinfoservice.domain.StudentInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import com.reactivespring.studentsinfoservice.repository.StudentsInfoRepository;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class StudentsInfoService {
    private final StudentsInfoRepository studentsInfoRepository;

    public StudentsInfoService(StudentsInfoRepository studentsInfoRepository) {
        this.studentsInfoRepository = studentsInfoRepository;
    }

    public Flux<StudentInfo> findAllStudentsInfo() {
        return studentsInfoRepository.findAll().log();
    }

    public Mono<StudentInfo> findStudentById(Integer studentId) {
        return studentsInfoRepository.findById(studentId).log();
    }

    public Mono<StudentInfo> addStudentInfo(StudentInfo studentInfo) {
        return studentsInfoRepository.save(studentInfo).log();
    }

    public Mono<StudentInfo> updatedStudentInfo(StudentInfo updatedStudentInfo, Integer studentId) {
        log.info("Student ID that is given" + studentId);
        return studentsInfoRepository.findById(studentId)
                .flatMap(studentInfo -> {
                    log.info("Here is the current student info "+studentInfo);
                    studentInfo.setStudent_id(updatedStudentInfo.getStudent_id());
                    studentInfo.setFirst_name(updatedStudentInfo.getFirst_name());
                    studentInfo.setLast_name(updatedStudentInfo.getLast_name());
                    studentInfo.setEmail(updatedStudentInfo.getEmail());
                    studentInfo.setAge(updatedStudentInfo.getAge());

                    return studentsInfoRepository.save(studentInfo);
                });
    }

    public Mono<Void> deleteStudentInfo(Integer studentId) {
        return studentsInfoRepository.deleteById(studentId).log();
    }

    public Flux<StudentInfo> findStudentInfoByEmail(String email) {
        return studentsInfoRepository.findByEmail(email).log();
    }
}
