package com.tolgaziftci.casestudy.services;

import com.tolgaziftci.casestudy.dto.AddMovieDTO;
import com.tolgaziftci.casestudy.models.Movie;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class MappingService {
    public Movie convertDTOToMovie(AddMovieDTO dto) {
        return new Movie(0, dto.getTitle(), dto.getPublishYear(), dto.getRated(),
                LocalDate.parse(dto.getReleased(), DateTimeFormatter.ofPattern("dd MMM yyyy")), dto.getRuntime(),
                dto.getGenre(), dto.getDirector(), dto.getWriter(), dto.getActors(), dto.getPlot(), dto.getLanguage(),
                dto.getCountry(), dto.getAwards(), dto.getPoster(), dto.getMetascore(), dto.getImdbRating(),
                dto.getImdbVotes(), dto.getImdbID(), dto.getType(), dto.getTotalSeasons(), dto.getComingSoon());
    }
}
