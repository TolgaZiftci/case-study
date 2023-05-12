package com.tolgaziftci.casestudy.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Movie {
    private Integer id;
    @NonNull
    private String title;
    private String publishYear;
    private String rated;
    private LocalDate released;
    private String runtime;
    private String genre;
    @NonNull
    private String director;
    @NonNull
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private Integer metascore;
    @NonNull
    private Double imdbRating;
    private Integer imdbVotes;
    private String imdbID;
    @NonNull
    private String type;
    private Integer totalSeasons;
    private Boolean comingSoon;
}
