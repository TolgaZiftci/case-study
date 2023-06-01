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
import java.util.List;

@RestController
@Slf4j
public class MovieControllerImpl implements MovieController {
    private final MovieService movieService;
    private final MappingService mappingService;

    public MovieControllerImpl(MovieService movieService, MappingService mappingService) {
        this.movieService = movieService;
        this.mappingService = mappingService;
    }

    @Override
    public List<MovieEntity> getMovies() {
        log.info("Getting list of all movies");
        return movieService.getAllMovies();
    }

    @Override
    public Integer getMovieCount() {
        log.info("Getting movie count");
        return movieService.getMovieCount();
    }

    @Override
    public MovieEntity getMovieById(Integer id) {
        log.info("Getting movie with id " + id);
        MovieEntity movie;
        if ((movie = movieService.getMovieById(id)) != null) return movie;
        else throw new MovieNotFoundException(id);
    }

    @Override
    public Movie addMovie(MovieDTO dto) {
        log.info("Attempting to add movie '" + dto.getTitle() + "'");
        if (movieService.getMovieByTitle(dto.getTitle()) == null) {
            Movie movie = mappingService.convertDTOToMovie(dto);
            movieService.addMovie(movie);
            log.info("Successfully added with id " + movie.getId());
            return movie;
        } else throw new MovieAlreadyExistsException(dto.getTitle());
    }

    @Override
    public Movie updateMovie(Integer id, MovieDTO dto) {
        log.info("Attempting to update movie " + id);
        if (movieService.getMovieById(id) != null) {
            Movie movie = mappingService.convertDTOToMovie(dto);
            movie.setId(id);
            movieService.updateMovie(movie);
            log.info("Successfully updated movie " + id);
            return movie;
        } else throw new MovieNotFoundException(id);
    }

    @Override
    public void deleteMovie(Integer id) {
        log.info("Deleting movie " + id);
        if (movieService.getMovieById(id) != null)
            movieService.deleteMovie(MovieEntityUtils.convertEntityToMovie(movieService.getMovieById(id)));
        else throw new MovieNotFoundException(id);
    }

    @Override
    public List<MovieEntity> filterMovies(Double imdbRating,
                                          Boolean greaterThan,
                                          String director,
                                          String type) {
        log.info("Filtering movies");
        return movieService.filterMovies(imdbRating, greaterThan, director, type);
    }

    @Override
    public List<MovieEntity> searchMovies(String title) {
        log.info("Searching movies by title");
        return movieService.searchMoviesByTitle(title);
    }
}
