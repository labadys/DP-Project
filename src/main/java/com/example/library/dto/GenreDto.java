package com.example.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO для жанра")
public class GenreDto {
    private Long id;
    private String name;
    private String description;
}