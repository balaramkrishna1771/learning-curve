package com.reactivespring.studentspaymentservice.dto;


import io.r2dbc.spi.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private float amount;
    private Integer paymentId;
}
