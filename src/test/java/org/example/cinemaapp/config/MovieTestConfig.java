package org.example.cinemaapp.config;

import org.example.cinemaapp.service.MovieService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MovieTestConfig {

    @Bean
    public MovieService movieService() {
        return Mockito.mock(MovieService.class);
    }
}
