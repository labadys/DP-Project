package com.example.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "DTO для автора")
public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String biography;
    private String email;
}