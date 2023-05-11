package com.tolgaziftci.casestudy.services;

import com.tolgaziftci.casestudy.models.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    Movie getMovieById(int id);

    void addMovie(Movie movie);

    void deleteMovie(Movie movie);

    void updateMovie(Movie movie);

    int getMovieCount();

    List<Movie> filterMovies(Double rating, Boolean greaterThan, String director, String type);

    List<Movie> searchMoviesByTitle(String title);

}
