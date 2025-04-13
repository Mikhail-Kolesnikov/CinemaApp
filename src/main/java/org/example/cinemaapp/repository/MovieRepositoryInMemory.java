package org.example.cinemaapp.repository;

import org.example.cinemaapp.entity.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieRepositoryInMemory {

    public Movie add(Movie newMovie);
    public List<Movie> findAll();
    public Optional<Movie> findById(Integer id);
    public List<Movie> findByTitle(String title);
    public List<Movie> findByReleaseDate(LocalDate releaseDate);
    void deleteById(Integer id);


}
