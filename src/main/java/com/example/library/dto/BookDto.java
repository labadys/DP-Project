// BookDto.java
package com.example.library.dto;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private Integer publicationYear;
    private String isbn;
    private String publisher;
    private String genre; // Добавьте это поле
}