package org.example.cinemaapp.controller;

import org.example.cinemaapp.config.MovieTestConfig;
import org.example.cinemaapp.dto.MovieResponseDto;
import org.example.cinemaapp.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
@Import(MovieTestConfig.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieService movieService;

    @Test
    void testFindAll() throws Exception {
        MovieResponseDto dto = new MovieResponseDto(
                1,
                "The Matrix",
                "A sci-fi classic",
                LocalDate.of(1999, 3, 31),
                "COMING_SOON",
                "Action"
        );

        when(movieService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("The Matrix"))
                .andExpect(jsonPath("$[0].genreName").value("Action"));
    }
}

