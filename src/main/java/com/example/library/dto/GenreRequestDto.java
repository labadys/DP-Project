package com.example.library.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class GenreRequestDto {
    @NotBlank(message = "Genre name is required")
    private String name;

    private String description;
}