package org.example.cinemaapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDto {
    private String title;
    private String description;
    private LocalDate releaseDate;
    private String genreName;

}

