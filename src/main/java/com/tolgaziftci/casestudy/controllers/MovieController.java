package com.tolgaziftci.casestudy.controllers;

import com.tolgaziftci.casestudy.dto.AddMovieDTO;
import com.tolgaziftci.casestudy.exceptions.MovieAlreadyExistsException;
import com.tolgaziftci.casestudy.exceptions.MovieNotFoundException;
import com.tolgaziftci.casestudy.models.Movie;
import com.tolgaziftci.casestudy.services.MappingService;
import com.tolgaziftci.casestudy.services.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {
    private final MovieService movieService;
    private final MappingService mappingService;

    public MovieController(MovieService movieService, MappingService mappingService) {
        this.movieService = movieService;
        this.mappingService = mappingService;
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

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody AddMovieDTO dto){
        if (movieService.getMovieByTitle(dto.getTitle()) == null){
            Movie movie = mappingService.convertDTOToMovie(dto);
            movieService.addMovie(movie);
            return movie;
        }
        else throw new MovieAlreadyExistsException(dto.getTitle());
    }

    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable("id") Integer id) {
        if (movieService.getMovieById(id) != null) movieService.deleteMovie(movieService.getMovieById(id));
        else throw new MovieNotFoundException(id);
    }
}
