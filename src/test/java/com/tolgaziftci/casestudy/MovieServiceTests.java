package com.tolgaziftci.casestudy;

import com.tolgaziftci.casestudy.models.Movie;
import com.tolgaziftci.casestudy.models.MovieEntity;
import com.tolgaziftci.casestudy.repositories.MovieRepository;
import com.tolgaziftci.casestudy.services.MovieService;
import com.tolgaziftci.casestudy.services.MovieServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MovieServiceTests {
    private MovieService movieService;

    private List<Movie> movieList = new ArrayList<>();

    @Mock
    private MovieRepository movieRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        movieService = new MovieServiceImpl(movieRepository);

        movieList = prepareTestData();
    }

    @Test
    public void getAllMovies() {
        when(movieRepository.getAll()).thenReturn(movieList);

        List<MovieEntity> movies = movieService.getAllMovies();

        assertEquals(3, movies.size());
    }

    @Test
    public void getMovieCount() {
        when(movieRepository.count()).thenReturn(movieList.size());

        int count = movieService.getMovieCount();

        assertEquals(3, count);
        verify(movieRepository, times(1)).count();
    }

    @Test
    public void addMovie() {
        Movie movie = Movie.builder()
                .id(4)
                .title("test")
                .genre("")
                .director("test")
                .writer("")
                .actors("")
                .language("")
                .country("")
                .imdbRating(8.5)
                .type("movie")
                .build();
        doAnswer(invocationOnMock -> movieList.add(invocationOnMock.getArgument(0))).when(movieRepository).save(movie);
        when(movieRepository.getAll()).thenReturn(movieList);

        movieService.addMovie(movie);

        assertEquals(4, movieService.getAllMovies().size());
    }

    @Test
    public void deleteMovie() {
        Movie movie = movieList.get(0);
        doAnswer(invocationOnMock -> movieList.remove(0)).when(movieRepository).delete(movie);
        when(movieRepository.getAll()).thenReturn(movieList);

        movieService.deleteMovie(movie);

        verify(movieRepository, times(1)).delete(movie);
        assertEquals(2, movieService.getAllMovies().size());
    }

    @Test
    public void filterMovies() {
        when(movieRepository.getAll()).thenReturn(movieList);

        List<MovieEntity> filtered = movieService.filterMovies(8.0, true, null, "movie");

        assertEquals(1, filtered.size());
        assertEquals("Avatar", filtered.get(0).getTitle());
    }

    private static List<Movie> prepareTestData() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(
                Movie.builder()
                        .id(1)
                        .title("Avatar")
                        .genre("")
                        .director("James Cameron")
                        .writer("")
                        .actors("")
                        .language("")
                        .country("")
                        .imdbRating(8.5)
                        .type("movie")
                        .build()
        );
        movieList.add(
                Movie.builder()
                        .id(2)
                        .title("300")
                        .genre("")
                        .director("Zack Snyder")
                        .writer("")
                        .actors("")
                        .language("")
                        .country("")
                        .imdbRating(7.5)
                        .type("movie")
                        .build()
        );
        movieList.add(
                Movie.builder()
                        .id(3)
                        .title("Game of Thrones")
                        .genre("")
                        .director("N/A")
                        .writer("")
                        .actors("")
                        .language("")
                        .country("")
                        .imdbRating(6.5)
                        .type("series")
                        .build()
        );
        return movieList;
    }
}
