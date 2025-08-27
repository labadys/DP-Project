package com.example.library.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class AuthorRequestDto {
    @NotBlank(message = "Author name is required")
    private String name;

    private String biography;
}