package org.example.cinemaapp.service;

import org.example.cinemaapp.dto.MovieRequestDto;
import org.example.cinemaapp.dto.MovieResponseDto;
import org.example.cinemaapp.entity.Genre;
import org.example.cinemaapp.entity.Movie;
import org.example.cinemaapp.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        movieForAdd.setStatus("COMING_SOON");

        Genre genre = new Genre(null, request.getGenreName());
        movieForAdd.setGenre(genre);

        Movie movieAfterSave = repository.add(movieForAdd);

        return new MovieResponseDto(movieAfterSave.getId(),
                movieAfterSave.getTitle(),
                movieAfterSave.getDescription(),
                movieAfterSave.getReleaseDate(),
                movieAfterSave.getStatus(),
                movieAfterSave.getGenre().getName());
    }
    public List<MovieResponseDto> findAll() {
        return repository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    public Optional<MovieResponseDto> findById(Integer id) {
        return repository.findById(id)
                .map(this::convertToDto);
    }

    public List<MovieResponseDto> findByTitle(String title) {
        return repository.findByTitle(title).stream()
                .map(this::convertToDto)
                .toList();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    private MovieResponseDto convertToDto(Movie movie) {
        return new MovieResponseDto(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getReleaseDate(),
                movie.getStatus(),
                movie.getGenre().getName()
        );
    }
}
