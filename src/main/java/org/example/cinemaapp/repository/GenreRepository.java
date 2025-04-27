package org.example.cinemaapp.repository;

import org.example.cinemaapp.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
//@Component
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    Optional<Genre> findByName(String name);
}
