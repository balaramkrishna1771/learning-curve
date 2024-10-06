package com.reactivespring.studentsinfoservice.exception;

public class StudentInfoNotFoundException extends RuntimeException{

    public StudentInfoNotFoundException(String message){
        super(message);
    }
}
