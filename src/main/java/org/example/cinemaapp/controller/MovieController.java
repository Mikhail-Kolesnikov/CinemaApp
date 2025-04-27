package org.example.cinemaapp.controller;

import jakarta.validation.Valid;
import org.example.cinemaapp.dto.MovieRequestDto;
import org.example.cinemaapp.dto.MovieResponseDto;
import org.example.cinemaapp.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @PostMapping
    public MovieResponseDto addMovie(@Valid @RequestBody MovieRequestDto request) {
        return service.addMovie(request);
    }

    @GetMapping
    public List<MovieResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public MovieResponseDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/search")
    public List<MovieResponseDto> findByTitle(@RequestParam String title) {
        return service.findByTitle(title);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
