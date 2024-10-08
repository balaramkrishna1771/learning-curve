package com.reactivespring.studentsservice.exception;

public class PaymentsServerException extends RuntimeException{
    private String message;
    public PaymentsServerException(String message){
        this.message = message;
    }
}
