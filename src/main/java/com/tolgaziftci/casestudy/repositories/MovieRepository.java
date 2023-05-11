package com.tolgaziftci.casestudy.repositories;

import com.tolgaziftci.casestudy.models.Movie;
import org.apache.ibatis.annotations.*;

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

    @Update("update movies set title=#{title}, publishyear=#{publishYear}, rated=#{rated}, released=#{released}, " +
            "runtime=#{runtime}, genre=#{genre}, director=#{director}, writer=#{writer}, actors=#{actors}, " +
            "plot=#{plot}, language=#{language}, country=#{country}, awards=#{awards}, poster=#{poster}, " +
            "metascore=#{metascore}, imdbrating=#{imdbRating}, imdbvotes=#{imdbVotes}, imdbID=#{imdbID}, " +
            "type=#{type}, totalseasons=#{totalSeasons}, comingsoon=#{comingSoon} where id=#{id}")
    void update(Movie movie);

    @Delete("delete from movies where id=#{id}")
    void delete(Movie movie);
}
