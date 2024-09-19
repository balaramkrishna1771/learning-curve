package com.reactivespring.medibuddyapp.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("student_info")
public class StudentInfo {


    @Id
    private Long id;
    @NotBlank(message = "Name mush be present")
    private String name;


    private String email;
    @NotNull
    @Positive(message = "Age must be positive")
    private byte age;
    private String gender;
    private String username;
    private String password;
}
