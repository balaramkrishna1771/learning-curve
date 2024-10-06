package com.reactivespring.studentsinfoservice.errorhandler;

import com.reactivespring.studentsinfoservice.exception.StudentInfoNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handleValidationErrors(WebExchangeBindException ex){
        log.error("Error caught in handleValidationErrors: {}",ex.getMessage(),ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ex.getBindingResult().getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .sorted()
                        .collect(Collectors.joining(","))
        );
    }

    @ExceptionHandler(StudentInfoNotFoundException.class)
    public ResponseEntity<String> handleStudentInfoNotFoundException(StudentInfoNotFoundException ex){
        log.error("Exception caught in handleStudentInfoNotFoundException : {}",ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
