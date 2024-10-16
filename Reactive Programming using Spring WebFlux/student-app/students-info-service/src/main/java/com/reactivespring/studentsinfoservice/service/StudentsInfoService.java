package com.reactivespring.studentsinfoservice.service;

import com.reactivespring.studentsinfoservice.domain.StudentInfo;
import com.reactivespring.studentsinfoservice.dto.StudentInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import com.reactivespring.studentsinfoservice.repository.StudentsInfoRepository;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Service
@Slf4j
public class StudentsInfoService {


    private final StudentsInfoRepository studentsInfoRepository;

    private final StudentInfoMapper studentInfoMapper = new StudentInfoMapper();

    private RabbitTemplate rabbitTemplate;

    public StudentsInfoService(StudentsInfoRepository studentsInfoRepository, RabbitTemplate rabbitTemplate) {
        this.studentsInfoRepository = studentsInfoRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "${rabbitmq.queue.studentsInfoQueue}")
    public void handleStudentInfoRequest(Integer studentId){
        log.info("Request recieved for student info with id : {}",studentId);
        var studentData = studentsInfoRepository.findById(studentId).map(studentInfoMapper);

        rabbitTemplate.convertAndSend("${rabbitmq.exchange.studentServiceExchange}","${rabbitmq.routingKeys.studentsInfoRoutingKey}",studentData);

    }
    public Flux<StudentInfoDTO> findAllStudentsInfo() {
        return studentsInfoRepository.findAll()
                .map(studentInfoMapper);
    }

    public Mono<StudentInfoDTO> findStudentById(Integer studentId) {
        return studentsInfoRepository.findById(studentId)
                .map(studentInfoMapper)
                .log();
    }

    public Mono<StudentInfoDTO> addStudentInfo(StudentInfo studentInfo) {
        return studentsInfoRepository.save(studentInfo)
                .map(studentInfoMapper)
                .log();
    }

    public Mono<StudentInfoDTO> updatedStudentInfo(StudentInfo updatedStudentInfo, Integer studentId) {
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
                })
                .map(studentInfoMapper);
    }

    public Mono<Void> deleteStudentInfo(Integer studentId) {
        return studentsInfoRepository.deleteById(studentId).log();
    }

    public Flux<StudentInfoDTO> findStudentInfoByEmail(String email) {
        return studentsInfoRepository.findByEmail(email)
                .map(studentInfoMapper)
                .log();
    }


}
