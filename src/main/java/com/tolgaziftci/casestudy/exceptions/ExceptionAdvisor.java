package com.tolgaziftci.casestudy.exceptions;

import com.tolgaziftci.casestudy.responses.ResponseBuilder;
import com.tolgaziftci.casestudy.responses.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvisor {
    @ExceptionHandler(MovieNotFoundException.class)
    RestResponse movieNotFoundHandler(MovieNotFoundException ex) {
        return ResponseBuilder.send(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
    }

    @ExceptionHandler(MovieAlreadyExistsException.class)
    RestResponse movieAlreadyExistsHandler(MovieAlreadyExistsException ex) {
        return ResponseBuilder.send(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    RestResponse validationFailureHandler(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        String invalidParameter = ex.getParameter().getParameterType().getSimpleName();
        String inMethod = Objects.requireNonNull(ex.getParameter().getMethod()).getName();

        log.warn("Validation failure: " + invalidParameter + " in " + inMethod);
        return ResponseBuilder.send(HttpStatus.BAD_REQUEST.value(),
                invalidParameter + " validation failed in " + inMethod,
                errors);
    }

    @ExceptionHandler(DateTimeParseException.class)
    RestResponse dateParseFailureHandle(){
        return ResponseBuilder.send(HttpStatus.BAD_REQUEST.value(),
                "Date parsing failed while adding/updating movie",
                null);
    }
}
