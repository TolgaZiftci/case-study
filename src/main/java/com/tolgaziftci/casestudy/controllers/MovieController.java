package com.tolgaziftci.casestudy.controllers;

import com.tolgaziftci.casestudy.dto.MovieDTO;
import com.tolgaziftci.casestudy.models.Movie;
import com.tolgaziftci.casestudy.models.MovieEntity;
import com.tolgaziftci.casestudy.responses.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
            responseCode = "200", useReturnTypeSchema = true
    )
    @GetMapping("/movies")
    RestResponse<List<MovieEntity>> getMovies();

    @Operation(
            summary = "Get movie count",
            description = "Get the number of movies in the database",
            tags = {"get"}
    )
    @ApiResponse(
            responseCode = "200", useReturnTypeSchema = true
    )
    @GetMapping("/movies/count")
    RestResponse<Integer> getMovieCount();

    @Operation(
            summary = "Get movie by id",
            description = "Get a single movie object by its id",
            tags = {"get"}
    )
    @ApiResponse(
            responseCode = "200", useReturnTypeSchema = true
    )
    @ApiResponse(
            responseCode = "404",
            description = "Movie with given id not found. Payload will be null",
            content = @Content(
                    schema = @Schema(implementation = RestResponse.class)
            )
    )
    @GetMapping("/movies/{id}")
    RestResponse<MovieEntity> getMovieById(@PathVariable("id") Integer id);

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
            responseCode = "200", useReturnTypeSchema = true
    )
    @ApiResponse(
            responseCode = "400",
            description = "Movie with given title already exists, or input validation failed. Payload will be " +
                    "null for the former case, and contain all validation errors in the latter",
            content = @Content(
                    schema = @Schema(implementation = RestResponse.class)
            )
    )
//    @ApiResponse(
//            responseCode = "400",
//            description = "Validation for input data failed. Payload will contain a list of validation error messages",
//            content = @Content(
//                    schema = @Schema(implementation = RestResponse.class)
//            )
//    )
    @PostMapping("/movies")
    RestResponse<Movie> addMovie(@RequestBody @Valid MovieDTO dto);

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
            responseCode = "200", useReturnTypeSchema = true
    )
    @ApiResponse(
            responseCode = "404",
            description = "Movie with given id not found. Payload will be null",
            content = @Content(
                    schema = @Schema(implementation = RestResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Validation for input data failed. Payload will contain a list of validation error messages",
            content = @Content(
                    schema = @Schema(implementation = RestResponse.class)
            )
    )
    @PutMapping("/movies/{id}")
    RestResponse<Movie> updateMovie(@PathVariable("id") Integer id, @RequestBody @Valid MovieDTO dto);

    @Operation(
            summary = "Delete movie",
            description = "Deletes a movie from the database",
            tags = {"delete"}
    )
    @ApiResponse(
            responseCode = "200", useReturnTypeSchema = true
    )
    @ApiResponse(
            responseCode = "404",
            description = "Movie with given id not found. Payload will be null",
            content = @Content(
                    schema = @Schema(implementation = RestResponse.class)
            )
    )
    @DeleteMapping("/movies/{id}")
    RestResponse<MovieEntity> deleteMovie(@PathVariable("id") Integer id);

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
            responseCode = "200", useReturnTypeSchema = true
    )
    @GetMapping("/movies/filter")
    RestResponse<List<MovieEntity>> filterMovies(@RequestParam(required = false) Double imdbRating,
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
            responseCode = "200", useReturnTypeSchema = true
    )
    @GetMapping("/movies/search")
    RestResponse<List<MovieEntity>> searchMovies(@RequestParam String title);
}
