package com.example.library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.Set;

@Data
public class BookRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private String isbn;

    private Long authorId;
    private Set<Long> genreIds;
}