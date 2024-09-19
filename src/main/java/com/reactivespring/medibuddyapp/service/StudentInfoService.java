package com.reactivespring.medibuddyapp.service;

import com.reactivespring.medibuddyapp.domain.StudentInfo;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.reactivespring.medibuddyapp.repository.StudentInfoRepository;

@Service
public class StudentInfoService {

    @Autowired
    private StudentInfoRepository studentInfoRepository;

    public Flux<StudentInfo> sampleStudentInfo(){
        return studentInfoRepository.findAll();

    }

    public Mono<StudentInfo> getStudentById(Long id) {
        return studentInfoRepository.findById(id).log();
    }

    public Mono<StudentInfo> addStudentInfo(StudentInfo studentInfo) {
        return studentInfoRepository.save(studentInfo).log();
    }

    public Mono<StudentInfo> updateStudentInfo(StudentInfo updatedStudentInfo, Long id) {

        return studentInfoRepository.findById(id)
                .flatMap(movieInfo -> {
                    movieInfo.setName(updatedStudentInfo.getName());
                    movieInfo.setAge(updatedStudentInfo.getAge());
                    movieInfo.setEmail(updatedStudentInfo.getEmail());
                    movieInfo.setGender(updatedStudentInfo.getGender());
                    movieInfo.setEmail(updatedStudentInfo.getEmail());
                    movieInfo.setPassword(updatedStudentInfo.getPassword());

                    return studentInfoRepository.save(movieInfo).log();
                });
    }

    public Mono<Void> deleteStudentInfo(Long id) {
        return studentInfoRepository.deleteById(id).log();
    }

    public Mono<StudentInfo> getStudentByAge(byte age) {
        return studentInfoRepository.findStudentByAge(age);
    }
}
