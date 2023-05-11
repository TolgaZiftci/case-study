package com.tolgaziftci.casestudy;

import com.tolgaziftci.casestudy.models.Movie;
import com.tolgaziftci.casestudy.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
public class CaseStudyApplication implements CommandLineRunner {
    @Autowired
    private MovieService movieService;

    public static void main(String[] args) {
        SpringApplication.run(CaseStudyApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(movieService.getMovieById(1));
        List<Movie> movies = movieService.getAllMovies();
        System.out.println(movies.size());
        for (Movie index : movies) {
            System.out.println(index);
        }
        Movie movie = new Movie(0, "Avatar", "2009", "PG-13", LocalDate.parse("18 Dec 2009", DateTimeFormatter.ofPattern("dd MMM yyyy")), "162 min", "Action, Adventure, Fantasy", "James Cameron", "James Cameron", "Sam Worthington, Zoe Saldana, Sigourney Weaver, Stephen Lang", "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", "English, Spanish", "USA, UK", "Won 3 Oscars. Another 80 wins & 121 nominations.", "http://ia.media-imdb.com/images/M/MV5BMTYwOTEwNjAzMl5BMl5BanBnXkFtZTcwODc5MTUwMw@@._V1_SX300.jpg", 83, 7.9, 890617, "tt0499549", "movie", null, false);
        movieService.addMovie(movie);
        System.out.println(movie);
        movie.setTitle("Not Avatar");
        movieService.updateMovie(movie);
        System.out.println(movieService.getMovieById(17));
        movieService.deleteMovie(movie);
        System.out.println(movieService.getMovieById(17));
    }
}
