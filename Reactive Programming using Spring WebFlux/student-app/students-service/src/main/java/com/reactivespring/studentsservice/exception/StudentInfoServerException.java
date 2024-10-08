package com.reactivespring.studentsservice.exception;

public class StudentInfoServerException extends RuntimeException{
    private String message;

    public StudentInfoServerException(String message) {
        super(message);
        this.message = message;
    }
}
