package com.tolgaziftci.casestudy.repositories;

import com.tolgaziftci.casestudy.models.Movie;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MovieRepository {
    List<Movie> getAll();

    Movie getById(int id);

    Movie getByTitle(String title);

    void save(Movie movie);

    void update(Movie movie);

    void delete(Movie movie);

    int count();
}
