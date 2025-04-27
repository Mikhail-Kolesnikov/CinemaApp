package org.example.cinemaapp.repository;

import org.example.cinemaapp.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {


    public List<Movie> findByTitle(String title);


}
