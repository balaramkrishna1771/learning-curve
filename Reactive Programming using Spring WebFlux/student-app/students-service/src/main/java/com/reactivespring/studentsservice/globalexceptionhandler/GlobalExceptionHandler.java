package com.reactivespring.studentsservice.globalexceptionhandler;

import com.reactivespring.studentsservice.exception.PaymentsClientException;
import com.reactivespring.studentsservice.exception.StudentInfoClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.StubNotFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentInfoClientException.class)
    public ResponseEntity<String> handleClientException(StudentInfoClientException exception){
        log.error("Error caught in handleClientException" + exception.getMessage());
        return ResponseEntity.status(exception.getStatusCode()).body(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException exception){
        log.error("Error caught in handleRuntimeException" + exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler(PaymentsClientException.class)
    public ResponseEntity<String> handleClientPaymentException(PaymentsClientException exception){
        log.error("Error caught in handleClientPaymentException" + exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}

