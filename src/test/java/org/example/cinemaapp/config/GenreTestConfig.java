package org.example.cinemaapp.config;

import org.example.cinemaapp.service.GenreService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class GenreTestConfig {

    @Bean
    public GenreService genreService() {
        return Mockito.mock(GenreService.class);
    }
}
