package com.reactivespring.studentsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private StudentInfoDTO studentInfoDTO;
    private List<PaymentDTO> paymentsDTO;

}
