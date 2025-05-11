package org.example.cinemaapp.controller;

import org.example.cinemaapp.config.GenreTestConfig;
import org.example.cinemaapp.dto.GenreDto;
import org.example.cinemaapp.service.GenreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GenreController.class)
@Import(GenreTestConfig.class) // подключаем конфигурацию с моками
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GenreService genreService;

    @Test
    void testFindAll() throws Exception {
        List<GenreDto> genres = List.of(new GenreDto(1, "Drama"));
        when(genreService.findAll()).thenReturn(genres);

        mockMvc.perform(get("/genres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Drama"));
    }
}
