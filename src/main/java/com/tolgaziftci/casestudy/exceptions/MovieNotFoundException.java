package com.tolgaziftci.casestudy.exceptions;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(Integer id) {
        super("Could not find movie with id: " + id);
    }
}
