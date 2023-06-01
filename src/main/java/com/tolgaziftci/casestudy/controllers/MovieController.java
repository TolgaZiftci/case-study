package com.tolgaziftci.casestudy.controllers;

import com.tolgaziftci.casestudy.dto.MovieDTO;
import com.tolgaziftci.casestudy.exceptions.ExceptionResponse;
import com.tolgaziftci.casestudy.models.Movie;
import com.tolgaziftci.casestudy.models.MovieEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Movies", description = "Movie data management endpoints")
@RequestMapping("/api")
public interface MovieController {
    @Operation(
            summary = "Get all movies",
            description = "Get a list containing all movie data",
            tags = {"get"}
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = MovieEntity.class))
            )
    )
    @GetMapping("/movies")
    List<MovieEntity> getMovies();

    @Operation(
            summary = "Get movie count",
            description = "Get the number of movies in the database",
            tags = {"get"}
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Integer.class)
            )
    )
    @GetMapping("/movies/count")
    Integer getMovieCount();

    @Operation(
            summary = "Get movie by id",
            description = "Get a single movie object by its id",
            tags = {"get"}
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MovieEntity.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Movie with given id not found",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @GetMapping("/movies/{id}")
    MovieEntity getMovieById(@PathVariable("id") Integer id);

    @Operation(
            summary = "Add movie",
            description = "Adds a new movie to the database",
            tags = {"post"}
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "'released' field date format must be dd MMM yyyy (e.g. 23 Feb 2023), " +
                    "'imdbRating' field must be between 0.0 and 10.0"
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Movie.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Movie with given title already exists",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Validation for input data failed",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @PostMapping("/movies")
    Movie addMovie(@RequestBody @Valid MovieDTO dto);

    @Operation(
            summary = "Update movie",
            description = "Updates the data of a movie in the database",
            tags = {"put"}
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "'released' field date format must be dd MMM yyyy (e.g. 23 Feb 2023), " +
                    "'imdbRating' field must be between 0.0 and 10.0"
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Movie.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Movie with given id not found",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Validation for input data failed",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @PutMapping("/movies/{id}")
    Movie updateMovie(@PathVariable("id") Integer id, @RequestBody @Valid MovieDTO dto);

    @Operation(
            summary = "Delete movie",
            description = "Deletes a movie from the database",
            tags = {"delete"}
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema()
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Movie with given id not found",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @DeleteMapping("/movies/{id}")
    void deleteMovie(@PathVariable("id") Integer id);

    @Operation(
            summary = "Filter movies",
            description = "Get a list containing all movie data that fits into the given filters",
            tags = {"get"}
    )
    @Parameter(
            name = "imdbRating",
            in = ParameterIn.QUERY,
            description = "Rating threshold for filter, used together with greaterThan"
    )
    @Parameter(
            name = "greaterThan",
            in = ParameterIn.QUERY,
            description = "Filter above or below threshold, used together with imdbRating"
    )
    @Parameter(
            name = "director",
            in = ParameterIn.QUERY,
            description = "Director name based filtering"
    )
    @Parameter(
            name = "type",
            in = ParameterIn.QUERY,
            description = "Type based filtering, only possible values are 'movie' and 'series'"
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = MovieEntity.class))
            )
    )
    @GetMapping("/movies/filter")
    List<MovieEntity> filterMovies(@RequestParam(required = false) Double imdbRating,
                                   @RequestParam(required = false) Boolean greaterThan,
                                   @RequestParam(required = false) String director,
                                   @RequestParam(required = false) String type);

    @Operation(
            summary = "Search movies",
            description = "Get a list containing all movie data with the title containing the given string",
            tags = {"get"}
    )
    @Parameter(
            name = "title",
            in = ParameterIn.QUERY,
            description = "String to search in titles",
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = MovieEntity.class))
            )
    )
    @GetMapping("/movies/search")
    List<MovieEntity> searchMovies(@RequestParam String title);
}
