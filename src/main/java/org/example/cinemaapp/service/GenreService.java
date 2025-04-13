package org.example.cinemaapp.service;


import org.example.cinemaapp.dto.GenreDto;
import org.example.cinemaapp.entity.Genre;
import org.example.cinemaapp.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
    public class GenreService {

        private final GenreRepository repository;

        public GenreService(GenreRepository repository) {
            this.repository = repository;
        }

        public GenreDto addGenre(GenreDto dto) {
            Genre genre = new Genre(null, dto.getName());
            Genre saved = repository.add(genre);
            return new GenreDto(saved.getId(), saved.getName());
        }

        public List<GenreDto> findAll() {
            return repository.findAll().stream()
                    .map(g -> new GenreDto(g.getId(), g.getName()))
                    .collect(Collectors.toList());
        }

        public Optional<GenreDto> findById(Integer id) {
            return repository.findById(id)
                    .map(g -> new GenreDto(g.getId(), g.getName()));
        }

        public Optional<GenreDto> findByName(String name) {
            return repository.findByName(name)
                    .map(g -> new GenreDto(g.getId(), g.getName()));
        }
    }

