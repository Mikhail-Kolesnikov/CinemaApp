package org.example.cinemaapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private Integer id;
    private String title;
    private LocalDate releaseDate;
    private String status;
    private String description;
}
