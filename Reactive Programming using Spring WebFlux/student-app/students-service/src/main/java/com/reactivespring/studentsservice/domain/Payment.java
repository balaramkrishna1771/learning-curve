package com.reactivespring.studentsservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("payments")
public class Payment {



    @Column("student_id")
    private Integer studentId;
    private Float amount;
    @Id
    @Column("payment_id")
    private Integer paymentId;
}
