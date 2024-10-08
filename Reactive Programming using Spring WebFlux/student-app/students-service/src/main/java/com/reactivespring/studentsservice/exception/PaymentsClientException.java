package com.reactivespring.studentsservice.exception;

public class PaymentsClientException extends RuntimeException{

    private String message;
    private Integer statusCode;

    public PaymentsClientException(String message, Integer statusCode){
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }

}
