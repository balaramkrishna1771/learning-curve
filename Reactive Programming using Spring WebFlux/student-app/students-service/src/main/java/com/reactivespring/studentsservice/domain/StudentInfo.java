package com.reactivespring.studentsservice.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("student_infos")
public class StudentInfo {

    @Id
    private Integer student_id;


    private String first_name;
    private String last_name;
    private String email;
    @NotNull
    @Positive(message = "studentInfo.age need to be a positive number")
    private Integer age;

}
