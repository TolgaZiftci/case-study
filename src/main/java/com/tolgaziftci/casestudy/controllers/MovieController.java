package com.tolgaziftci.casestudy.controllers;

import com.tolgaziftci.casestudy.exceptions.MovieNotFoundException;
import com.tolgaziftci.casestudy.models.Movie;
import com.tolgaziftci.casestudy.services.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable("id") int id) {
        Movie movie;
        if ((movie = movieService.getMovieById(id)) != null) return movie;
        else throw new MovieNotFoundException(id);
    }

    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable("id") Integer id) {
        movieService.deleteMovie(movieService.getMovieById(id));
    }
}
