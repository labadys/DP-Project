package com.example.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO для создания/обновления жанра")
public class GenreRequestDto {
    @NotBlank(message = "Название жанра обязательно")
    private String name;

    private String description;
}
