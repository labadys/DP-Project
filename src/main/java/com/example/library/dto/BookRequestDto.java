package com.example.library.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class BookRequestDto {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotNull(message = "Publication year is required")
    private Integer publicationYear;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    private String publisher;

    private String genre;
}