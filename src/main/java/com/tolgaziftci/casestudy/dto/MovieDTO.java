package com.tolgaziftci.casestudy.dto;

import com.tolgaziftci.casestudy.annotations.BoundedValue;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class MovieDTO {
    @NotNull(message = "title must not be null")
    private String title;
    private String publishYear;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    @NotNull(message = "director must not be null")
    private String director;
    @NotNull(message = "writer must not be null")
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private Integer metascore;
    @NotNull(message = "IMDBrating must not be null")
    @BoundedValue(message = "IMDBrating must be between 0 and 10")
    private Double imdbRating;
    private Integer imdbVotes;
    private String imdbID;
    @NotNull(message = "type must not be null")
    private String type;
    private Integer totalSeasons;
    private Boolean comingSoon;
}
