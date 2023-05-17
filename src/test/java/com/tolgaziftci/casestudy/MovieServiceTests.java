package com.tolgaziftci.casestudy;

import com.tolgaziftci.casestudy.models.Movie;
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

    private final List<Movie> movieList = new ArrayList<>();

    @Mock
    private MovieRepository movieRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        movieService = new MovieServiceImpl(movieRepository);

        Movie movie = new Movie("Avatar", "James Cameron", "James Cameron", 8.5, "movie");
        movieList.add(movie);
        movie = new Movie("300", "Zack Snyder", "Zack Snyder", 7.5, "movie");
        movieList.add(movie);
        movie = new Movie("Game of Thrones", "N/A", "David Benioff", 6.5, "series");
        movieList.add(movie);
    }

    @Test
    public void getAllMovies() {
        when(movieRepository.getAll()).thenReturn(movieList);

        List<Movie> movies = movieService.getAllMovies();

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
        Movie movie = new Movie("test", "tolga ziftci", "tolga ziftci", 5.5, "movie");
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

        List<Movie> filtered = movieService.filterMovies(8.0, true, null, "movie");

        assertEquals(1, filtered.size());
        assertEquals("Avatar", filtered.get(0).getTitle());
    }
}
