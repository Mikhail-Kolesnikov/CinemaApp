package org.example.cinemaapp.repository;

import org.example.cinemaapp.entity.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreRepositoryInMemory implements GenreRepository {

    private final List<Genre> genres = new ArrayList<>();
    private int genreIdCounter = 0;

    @Override
    public Genre add(Genre genre) {
        genre.setId(++genreIdCounter);
        genres.add(genre);
        return genre;
    }

    @Override
    public List<Genre> findAll() {
        return genres;
    }

    @Override
    public Optional<Genre> findById(Integer id) {
        return genres.stream()
                .filter(g -> g.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Genre> findByName(String name) {
        return genres.stream()
                .filter(g -> g.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}
