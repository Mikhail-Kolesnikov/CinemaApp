package org.example.cinemaapp.service;

import org.example.cinemaapp.dto.GenreDto;
import org.example.cinemaapp.entity.Genre;
import org.example.cinemaapp.exception.NotFoundException;
import org.example.cinemaapp.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GenreServiceTest {

    private GenreRepository repository;
    private GenreService service;

    @BeforeEach
    void setUp() {
        repository = mock(GenreRepository.class);
        service = new GenreService(repository);
    }

    @Test
    void testAddGenre() {
        GenreDto dto = new GenreDto(null, "Comedy");
        Genre savedGenre = new Genre(1, "Comedy");

        when(repository.save(any(Genre.class))).thenReturn(savedGenre);

        GenreDto result = service.addGenre(dto);
        assertEquals("Comedy", result.getName());
        assertNotNull(result.getId());
    }

    @Test
    void testFindById_Found() {
        Genre genre = new Genre(1, "Action");
        when(repository.findById(1)).thenReturn(Optional.of(genre));

        GenreDto result = service.findById(1);
        assertEquals("Action", result.getName());
    }

    @Test
    void testFindById_NotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.findById(1));
    }
}
