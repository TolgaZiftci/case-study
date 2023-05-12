package com.tolgaziftci.casestudy.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private int status;
    private String message;
}
