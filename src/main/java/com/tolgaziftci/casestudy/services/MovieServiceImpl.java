package com.tolgaziftci.casestudy.services;

import com.tolgaziftci.casestudy.models.Movie;
import com.tolgaziftci.casestudy.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

    public int getMovieCount() {
        return movieRepository.count();
    }

    @Override
    public List<Movie> searchMoviesByTitle(String title) {
        List<Movie> movieList = getAllMovies();

        return movieList.stream()
                .filter(movie -> movie.getTitle().toLowerCase(Locale.ROOT).contains(title.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> filterMovies(Double rating, Boolean greaterThan, String director, String type) {
        List<Movie> movieList = getAllMovies();
        if (rating != null && greaterThan != null) {
            movieList = filterByRating(movieList, rating, greaterThan);
        }
        if (director != null) {
            movieList = filterByDirector(movieList, director);
        }
        if (type != null) {
            movieList = filterByType(movieList, type);
        }
        return movieList;
    }

    private List<Movie> filterByRating(List<Movie> movieList, Double rating, boolean greaterThan) {
        return greaterThan ? movieList.stream()
                .filter(movie -> movie.getImdbRating() >= rating)
                .collect(Collectors.toList())
                : movieList.stream()
                .filter(movie -> movie.getImdbRating() <= rating)
                .collect(Collectors.toList());
    }

    private List<Movie> filterByDirector(List<Movie> movieList, String director) {
        return movieList.stream()
                .filter(movie -> movie.getDirector().toLowerCase(Locale.ROOT)
                        .contains(director.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    private List<Movie> filterByType(List<Movie> movieList, String type) {
        return movieList.stream()
                .filter(movie -> movie.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
}
