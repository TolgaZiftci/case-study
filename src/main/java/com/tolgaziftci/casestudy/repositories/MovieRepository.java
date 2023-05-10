package com.tolgaziftci.casestudy.repositories;

import com.tolgaziftci.casestudy.models.Movie;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieRepository {
    @Select("select * from movies")
    List<Movie> getAll();

    @Select("select * from movies where id=#{id}")
    Movie getById(int id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into movies (" +
            "title, publishyear, rated, released, runtime, genre, director, writer, actors, " +
            "plot, language, country, awards, poster, metascore, imdbrating, imdbvotes, " +
            "imdbID, type, totalseasons, comingsoon" +
            ") values (" +
            "#{title}, #{publishYear}, #{rated}, #{released}, #{runtime}, #{genre}, " +
            "#{director}, #{writer}, #{actors}, #{plot}, #{language}, #{country}, " +
            "#{awards}, #{poster}, #{metascore}, #{imdbRating}, #{imdbVotes}, " +
            "#{imdbID}, #{type}, #{totalSeasons}, #{comingSoon})")
    void save(Movie movie);
}
