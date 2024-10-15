package com.reactivespring.studentspaymentservice.service;

import com.reactivespring.studentspaymentservice.domain.Payment;
import com.reactivespring.studentspaymentservice.dto.PaymentDTO;

import java.util.function.Function;

public class PaymentMapper implements Function<Payment, PaymentDTO> {

    @Override
    public PaymentDTO apply(Payment payment) {
        return new PaymentDTO(payment.getAmount(),payment.getPaymentId());
    }
}
