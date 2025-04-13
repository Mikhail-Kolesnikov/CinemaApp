package org.example.cinemaapp.repository;

import org.example.cinemaapp.entity.Genre;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface GenreRepository {
    Genre add(Genre genre);
    List<Genre> findAll();
    Optional<Genre> findById(Integer id);
    Optional<Genre> findByName(String name);
}
