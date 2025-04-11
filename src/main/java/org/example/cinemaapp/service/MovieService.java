package org.example.cinemaapp.service;

import org.example.cinemaapp.dto.MovieRequestDto;
import org.example.cinemaapp.dto.MovieResponseDto;
import org.example.cinemaapp.entity.Movie;
import org.example.cinemaapp.repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;

    }

    public MovieResponseDto addMovie(MovieRequestDto request) {
        Movie movieForAdd = new Movie();
        movieForAdd.setTitle(request.getTitle());
        movieForAdd.setReleaseDate(request.getReleaseDate());
        movieForAdd.setDescription(request.getDescription());

        Movie movieAfterSave = repository.add(movieForAdd);

        return new MovieResponseDto(movieAfterSave.getId(),
                movieAfterSave.getTitle(),
                movieAfterSave.getDescription(),
                movieAfterSave.getReleaseDate(),movieAfterSave.getStatus());
    }
}
