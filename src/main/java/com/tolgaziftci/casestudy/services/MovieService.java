package com.tolgaziftci.casestudy.services;

import com.tolgaziftci.casestudy.models.Movie;
import com.tolgaziftci.casestudy.models.MovieEntity;

import java.util.List;

public interface MovieService {
    List<MovieEntity> getAllMovies();

    MovieEntity getMovieById(int id);

    MovieEntity getMovieByTitle(String title);

    void addMovie(Movie movie);

    void deleteMovie(Movie movie);

    void updateMovie(Movie movie);

    int getMovieCount();

    List<MovieEntity> filterMovies(Double rating, Boolean greaterThan, String director, String type);

    List<MovieEntity> searchMoviesByTitle(String title);

}
