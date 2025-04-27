package org.example.cinemaapp.service;

import org.example.cinemaapp.dto.GenreDto;
import org.example.cinemaapp.entity.Genre;
import org.example.cinemaapp.exception.NotFoundException;
import org.example.cinemaapp.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private final GenreRepository repository;

    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    public GenreDto addGenre(GenreDto dto) {
        Genre genre = new Genre();
        genre.setName(dto.getName());

        Genre saved = repository.save(genre);
        return new GenreDto(saved.getId(), saved.getName());
    }

    public List<GenreDto> findAll() {
        return repository.findAll().stream()
                .map(g -> new GenreDto(g.getId(), g.getName()))
                .collect(Collectors.toList());
    }

    public GenreDto findById(Integer id) {
        Genre genre = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genre not found with id " + id));
        return new GenreDto(genre.getId(), genre.getName());
    }

    public GenreDto findByName(String name) {
        Genre genre = repository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Genre not found with name " + name));
        return new GenreDto(genre.getId(), genre.getName());
    }
}
