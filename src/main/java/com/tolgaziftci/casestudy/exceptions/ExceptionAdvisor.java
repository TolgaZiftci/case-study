package com.tolgaziftci.casestudy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExceptionAdvisor {
    @ExceptionHandler(MovieNotFoundException.class)
    ResponseEntity<Object> movieNotFoundHandler(MovieNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(MovieAlreadyExistsException.class)
    ResponseEntity<Object> movieAlreadyExistsHandler(MovieAlreadyExistsException ex) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
