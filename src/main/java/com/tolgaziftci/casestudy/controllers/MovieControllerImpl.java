package com.tolgaziftci.casestudy.controllers;

import com.tolgaziftci.casestudy.dto.MovieDTO;
import com.tolgaziftci.casestudy.exceptions.MovieAlreadyExistsException;
import com.tolgaziftci.casestudy.exceptions.MovieNotFoundException;
import com.tolgaziftci.casestudy.models.Movie;
import com.tolgaziftci.casestudy.models.MovieEntity;
import com.tolgaziftci.casestudy.responses.ResponseBuilder;
import com.tolgaziftci.casestudy.responses.RestResponse;
import com.tolgaziftci.casestudy.services.MappingService;
import com.tolgaziftci.casestudy.services.MovieService;
import com.tolgaziftci.casestudy.utils.MovieEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public RestResponse<List<MovieEntity>> getMovies() {
        log.info("Getting list of all movies");
        List<MovieEntity> movies = movieService.getAllMovies();
        return ResponseBuilder.send(HttpStatus.OK.value(), movies);
    }

    @Override
    public RestResponse<Integer> getMovieCount() {
        log.info("Getting movie count");
        Integer count = movieService.getMovieCount();
        return ResponseBuilder.send(HttpStatus.OK.value(), count);
    }

    @Override
    public RestResponse<MovieEntity> getMovieById(Integer id) {
        log.info("Getting movie with id " + id);
        MovieEntity movie;
        if ((movie = movieService.getMovieById(id)) != null)
            return ResponseBuilder.send(HttpStatus.OK.value(), movie);
        else throw new MovieNotFoundException(id);
    }

    @Override
    public RestResponse<Movie> addMovie(MovieDTO dto) {
        log.info("Attempting to add movie '" + dto.getTitle() + "'");
        if (movieService.getMovieByTitle(dto.getTitle()) == null) {
            Movie movie = mappingService.convertDTOToMovie(dto);
            movieService.addMovie(movie);
            log.info("Successfully added with id " + movie.getId());
            return ResponseBuilder.send(HttpStatus.OK.value(), "Successfully added movie", movie);
        } else throw new MovieAlreadyExistsException(dto.getTitle());
    }

    @Override
    public RestResponse<Movie> updateMovie(Integer id, MovieDTO dto) {
        log.info("Attempting to update movie " + id);
        if (movieService.getMovieById(id) != null) {
            Movie movie = mappingService.convertDTOToMovie(dto);
            movie.setId(id);
            movieService.updateMovie(movie);
            log.info("Successfully updated movie " + id);
            return ResponseBuilder.send(HttpStatus.OK.value(), "Successfully updated movie", movie);
        } else throw new MovieNotFoundException(id);
    }

    @Override
    public RestResponse<MovieEntity> deleteMovie(Integer id) {
        log.info("Deleting movie " + id);
        MovieEntity movie;
        if ((movie = movieService.getMovieById(id)) != null){
            movieService.deleteMovie(MovieEntityUtils.convertEntityToMovie(movieService.getMovieById(id)));
            return ResponseBuilder.send(HttpStatus.OK.value(), "Successfully deleted movie", movie);
        }
        else throw new MovieNotFoundException(id);
    }

    @Override
    public RestResponse<List<MovieEntity>> filterMovies(Double imdbRating,
                                          Boolean greaterThan,
                                          String director,
                                          String type) {
        log.info("Filtering movies");
        List<MovieEntity> filteredMovies = movieService.filterMovies(imdbRating, greaterThan, director, type);
        return ResponseBuilder.send(HttpStatus.OK.value(), filteredMovies);
    }

    @Override
    public RestResponse<List<MovieEntity>> searchMovies(String title) {
        log.info("Searching movies by title");
        List<MovieEntity> filteredMovies = movieService.searchMoviesByTitle(title);
        return ResponseBuilder.send(HttpStatus.OK.value(), filteredMovies);
    }
}
