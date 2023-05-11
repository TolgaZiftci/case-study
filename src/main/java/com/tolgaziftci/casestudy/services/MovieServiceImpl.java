package com.tolgaziftci.casestudy.services;

import com.tolgaziftci.casestudy.models.Movie;
import com.tolgaziftci.casestudy.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.getAll();
    }

    @Override
    public Movie getMovieById(int id) {
        return movieRepository.getById(id);
    }

    @Override
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }

    @Override
    public void updateMovie(Movie movie) {
        movieRepository.update(movie);
    }
}
