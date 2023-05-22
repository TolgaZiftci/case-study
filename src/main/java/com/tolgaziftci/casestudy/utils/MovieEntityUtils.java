package com.tolgaziftci.casestudy.utils;

import com.tolgaziftci.casestudy.models.Movie;
import com.tolgaziftci.casestudy.models.MovieEntity;

import java.util.Arrays;
import java.util.List;

public class MovieEntityUtils {
    private MovieEntityUtils() {
    }

    public static MovieEntity convertMovieToEntity(Movie movie) {
        if (movie == null) return null;
        return MovieEntity.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .publishYear(movie.getPublishYear())
                .rated(movie.getRated())
                .released(movie.getReleased())
                .runtime(movie.getRuntime())
                .genre(separateListFromString(movie.getGenre()))
                .director(movie.getDirector())
                .writer(separateListFromString(movie.getWriter()))
                .actors(separateListFromString(movie.getActors()))
                .plot(movie.getPlot())
                .language(separateListFromString(movie.getLanguage()))
                .country(separateListFromString(movie.getCountry()))
                .awards(movie.getAwards())
                .poster(movie.getPoster())
                .metascore(movie.getMetascore())
                .imdbRating(movie.getImdbRating())
                .imdbVotes(movie.getImdbVotes())
                .imdbID(movie.getImdbID())
                .type(movie.getType())
                .totalSeasons(movie.getTotalSeasons())
                .comingSoon(movie.getComingSoon())
                .build();
    }

    public static Movie convertEntityToMovie(MovieEntity entity) {
        if (entity == null) return null;
        return Movie.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .publishYear(entity.getPublishYear())
                .rated(entity.getRated())
                .released(entity.getReleased())
                .runtime(entity.getRuntime())
                .genre(concatenateListToString(entity.getGenre()))
                .director(entity.getDirector())
                .writer(concatenateListToString(entity.getWriter()))
                .actors(concatenateListToString(entity.getActors()))
                .plot(entity.getPlot())
                .language(concatenateListToString(entity.getLanguage()))
                .country(concatenateListToString(entity.getCountry()))
                .awards(entity.getAwards())
                .poster(entity.getPoster())
                .metascore(entity.getMetascore())
                .imdbRating(entity.getImdbRating())
                .imdbVotes(entity.getImdbVotes())
                .imdbID(entity.getImdbID())
                .type(entity.getType())
                .totalSeasons(entity.getTotalSeasons())
                .comingSoon(entity.getComingSoon())
                .build();
    }

    private static List<String> separateListFromString(String input) {
        return Arrays.asList(input.split(", "));
    }

    private static String concatenateListToString(List<String> input) {
        return String.join(", ", input);
    }
}
