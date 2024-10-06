package com.reactivespring.studentspaymentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("payments")
public class Payment {


    private Integer student_id;
    private Float amount;
    @Id
    private Integer payment_id;
}
