package org.example.cinemaapp.service;

import org.example.cinemaapp.dto.MovieRequestDto;
import org.example.cinemaapp.dto.MovieResponseDto;
import org.example.cinemaapp.entity.Genre;
import org.example.cinemaapp.entity.Movie;
import org.example.cinemaapp.exception.NotFoundException;
import org.example.cinemaapp.repository.GenreRepository;
import org.example.cinemaapp.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    public MovieResponseDto addMovie(MovieRequestDto request) {

        Genre genre = genreRepository.findByName(request.getGenreName())
                .orElseGet(() -> genreRepository.save(new Genre(null, request.getGenreName())));

        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setReleaseDate(request.getReleaseDate());
        movie.setDescription(request.getDescription());
        movie.setStatus("COMING_SOON");
        movie.setGenre(genre);

        Movie saved = movieRepository.save(movie);

        return convertToDto(saved);
    }

    public List<MovieResponseDto> findAll() {
        return movieRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public MovieResponseDto findById(Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Movie not found with id " + id));
        return convertToDto(movie);
    }

    public List<MovieResponseDto> findByTitle(String title) {
        List<Movie> movies = movieRepository.findByTitle(title);
        if (movies.isEmpty()) {
            throw new NotFoundException("No movies found with title " + title);
        }
        return movies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Integer id) {
        if (!movieRepository.existsById(id)) {
            throw new NotFoundException("Cannot delete. Movie not found with id " + id);
        }
        movieRepository.deleteById(id);
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
