package com.reactivespring.studentsservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private StudentInfo studentInfo;
    private List<Payment> payments;

}
