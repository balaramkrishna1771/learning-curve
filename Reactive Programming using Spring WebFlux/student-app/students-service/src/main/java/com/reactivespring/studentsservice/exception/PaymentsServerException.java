package com.reactivespring.studentsservice.exception;

public class PaymentsServerException extends RuntimeException{
    private String message;
    public PaymentsServerException(String message){
        super(message);
        this.message = message;
    }
}
