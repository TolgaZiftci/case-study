package com.tolgaziftci.casestudy.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvisor {
    @ExceptionHandler(MovieNotFoundException.class)
    ResponseEntity<Object> movieNotFoundHandler(MovieNotFoundException ex) {
        ExceptionResponse response = ExceptionResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(MovieAlreadyExistsException.class)
    ResponseEntity<Object> movieAlreadyExistsHandler(MovieAlreadyExistsException ex) {
        ExceptionResponse response = ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> validationFailureHandler(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        String invalidParameter = ex.getParameter().getParameterType().getSimpleName();
        String inMethod = Objects.requireNonNull(ex.getParameter().getMethod()).getName();
        log.warn("Validation failure: " + invalidParameter + " in " + inMethod);
        ExceptionResponse response = ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(invalidParameter + " validation failed in " + inMethod)
                .objects(errors)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
