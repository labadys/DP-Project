package com.example.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Schema(description = "DTO для создания/обновления автора")
public class AuthorRequestDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String biography;

    @Email
    private String email;
}