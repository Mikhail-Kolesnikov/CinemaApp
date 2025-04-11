package org.example.cinemaapp.repository;

import org.example.cinemaapp.entity.Movie;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MovieRepository implements MovieRepositoryInMemory{

    private List<Movie> database;
    private Integer idMovieCounter;

    public MovieRepository() {
        database = new ArrayList<>();
        idMovieCounter = 0;
    }

    @Override
    public Movie add(Movie newMovie) {
        newMovie.setId(++idMovieCounter);
        newMovie.setReleaseDate(LocalDate.now());
        newMovie.setTitle(newMovie.getTitle());
        database.add(newMovie);
        return newMovie;
    }

    @Override
    public List<Movie> findAll() {
        return database;
    }

    @Override
    public Optional<Movie> findById(Integer id) {
        return database.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return database.stream()
                .filter(movie -> movie.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> findByReleaseDate(LocalDate releaseDate) {
        return List.of();
    }
}
