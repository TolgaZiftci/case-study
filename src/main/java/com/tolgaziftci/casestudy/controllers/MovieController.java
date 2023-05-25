package com.tolgaziftci.casestudy.controllers;

import com.tolgaziftci.casestudy.dto.MovieDTO;
import com.tolgaziftci.casestudy.exceptions.MovieAlreadyExistsException;
import com.tolgaziftci.casestudy.exceptions.MovieNotFoundException;
import com.tolgaziftci.casestudy.models.Movie;
import com.tolgaziftci.casestudy.models.MovieEntity;
import com.tolgaziftci.casestudy.services.MappingService;
import com.tolgaziftci.casestudy.services.MovieService;
import com.tolgaziftci.casestudy.utils.MovieEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class MovieController {
    private final MovieService movieService;
    private final MappingService mappingService;

    public MovieController(MovieService movieService, MappingService mappingService) {
        this.movieService = movieService;
        this.mappingService = mappingService;
    }

    @GetMapping("/movies")
    public List<MovieEntity> getMovies() {
        log.info("Getting list of all movies");
        return movieService.getAllMovies();
    }

    @GetMapping("/movies/count")
    public Integer getMovieCount() {
        log.info("Getting movie count");
        return movieService.getMovieCount();
    }

    @GetMapping("/movies/{id}")
    public MovieEntity getMovieById(@PathVariable("id") Integer id) {
        log.info("Getting movie with id " + id);
        MovieEntity movie;
        if ((movie = movieService.getMovieById(id)) != null) return movie;
        else throw new MovieNotFoundException(id);
    }

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody @Valid MovieDTO dto) {
        log.info("Attempting to add movie '" + dto.getTitle() + "'");
        if (movieService.getMovieByTitle(dto.getTitle()) == null) {
            Movie movie = mappingService.convertDTOToMovie(dto);
            movieService.addMovie(movie);
            log.info("Successfully added with id " + movie.getId());
            return movie;
        } else throw new MovieAlreadyExistsException(dto.getTitle());
    }

    @PutMapping("/movies/{id}")
    public Movie updateMovie(@PathVariable("id") Integer id, @RequestBody @Valid MovieDTO dto) {
        log.info("Attempting to update movie " + id);
        if (movieService.getMovieById(id) != null) {
            Movie movie = mappingService.convertDTOToMovie(dto);
            movie.setId(id);
            movieService.updateMovie(movie);
            log.info("Successfully updated movie " + id);
            return movie;
        } else throw new MovieNotFoundException(id);
    }

    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable("id") Integer id) {
        log.info("Deleting movie " + id);
        if (movieService.getMovieById(id) != null)
            movieService.deleteMovie(MovieEntityUtils.convertEntityToMovie(movieService.getMovieById(id)));
        else throw new MovieNotFoundException(id);
    }

    @GetMapping("/movies/filter")
    public List<MovieEntity> filterMovies(@RequestParam(required = false) Double imdbRating,
                                          @RequestParam(required = false) Boolean greaterThan,
                                          @RequestParam(required = false) String director,
                                          @RequestParam(required = false) String type) {
        log.info("Filtering movies");
        return movieService.filterMovies(imdbRating, greaterThan, director, type);
    }

    @GetMapping("/movies/search")
    public List<MovieEntity> searchMovies(@RequestParam String title) {
        log.info("Searching movies by title");
        return movieService.searchMoviesByTitle(title);
    }
}
