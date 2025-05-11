package org.example.cinemaapp.service;


import org.example.cinemaapp.dto.MovieRequestDto;
import org.example.cinemaapp.dto.MovieResponseDto;
import org.example.cinemaapp.entity.Genre;
import org.example.cinemaapp.entity.Movie;
import org.example.cinemaapp.exception.NotFoundException;
import org.example.cinemaapp.repository.GenreRepository;
import org.example.cinemaapp.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    private MovieRepository movieRepository;
    private GenreRepository genreRepository;
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieRepository = mock(MovieRepository.class);
        genreRepository = mock(GenreRepository.class);
        movieService = new MovieService(movieRepository, genreRepository);
    }

    @Test
    void testAddMovie_createsAndReturnsMovie() {
        MovieRequestDto request = new MovieRequestDto(
                "Test Movie",
                "Test Description",
                LocalDate.now(),
                "Action"
        );

        Genre genre = new Genre(1, "Action");
        Movie movie = new Movie(1, "Test Movie", LocalDate.now(), "COMING_SOON", "Test Description", genre);

        when(genreRepository.findByName("Action")).thenReturn(Optional.of(genre));
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        MovieResponseDto response = movieService.addMovie(request);

        assertEquals("Test Movie", response.getTitle());
        assertEquals("Action", response.getGenreName());
    }

    @Test
    void testFindById_NotFound() {
        when(movieRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> movieService.findById(1));
    }

    @Test
    void testFindByTitle_NotFound() {
        when(movieRepository.findByTitle("Unknown")).thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> movieService.findByTitle("Unknown"));
    }
}
