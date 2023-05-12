package com.tolgaziftci.casestudy.exceptions;

public class MovieAlreadyExistsException extends RuntimeException {
    public MovieAlreadyExistsException(String title) {
        super("Movie with title '" + title + "' already exists");
    }
}
