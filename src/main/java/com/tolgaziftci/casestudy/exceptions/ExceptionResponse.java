package com.tolgaziftci.casestudy.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ExceptionResponse {
    private int status;
    private String message;
    private Object objects;
}
