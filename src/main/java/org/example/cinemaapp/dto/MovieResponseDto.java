package org.example.cinemaapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cinemaapp.entity.Genre;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDto {
    private Integer id;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private String status;
    private String genreName;
}
