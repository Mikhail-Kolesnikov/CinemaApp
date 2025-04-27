package org.example.cinemaapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {

    private Integer id;

    @NotBlank(message = "Genre name is mandatory")
    private String name;
}

