package com.reactivespring.studentsinfoservice.service;

import com.reactivespring.studentsinfoservice.domain.StudentInfo;
import com.reactivespring.studentsinfoservice.dto.StudentInfoDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StudentInfoMapper implements Function<StudentInfo, StudentInfoDTO> {


    @Override
    public StudentInfoDTO apply(StudentInfo studentInfo) {
        return new StudentInfoDTO(
                studentInfo.getFirst_name().concat(" ").concat(studentInfo.getLast_name()),
                studentInfo.getAge()
        );
    }
}
