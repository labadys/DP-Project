package com.example.library.dto;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String author;           // Имя автора (String)
    private Integer publicationYear;
    private String isbn;
    private String publisher;        // Название издательства (String)
    private String genre;            // Название жанра (String)

    public BookDto(long l, String newBook, String newAuthor, int i, String number, String newPublisher) {
    }
}