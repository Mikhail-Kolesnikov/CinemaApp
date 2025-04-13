package org.example.cinemaapp.controller;

import org.example.cinemaapp.dto.GenreDto;
import org.example.cinemaapp.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService service;

    public GenreController(GenreService service) {
        this.service = service;
    }

    @PostMapping
    public GenreDto addGenre(@RequestBody GenreDto dto) {
        return service.addGenre(dto);
    }

    @GetMapping
    public List<GenreDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<GenreDto> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/search")
    public Optional<GenreDto> findByName(@RequestParam String name) {
        return service.findByName(name);
    }
}
