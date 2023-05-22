package com.tolgaziftci.casestudy.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class MovieEntity {
    private Integer id;
    private String title;
    private String publishYear;
    private String rated;
    private LocalDate released;
    private String runtime;
    private List<String> genre;
    private String director;
    private List<String> writer;
    private List<String> actors;
    private String plot;
    private List<String> language;
    private List<String> country;
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
