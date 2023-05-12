package com.tolgaziftci.casestudy.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddMovieDTO {
    private String title;
    private String publishYear;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private Integer metascore;
    private Double imdbRating;
    private Integer imdbVotes;
    private String imdbID;
    private String type;
    private Integer totalSeasons;
    private Boolean comingSoon;
}
