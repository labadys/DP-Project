package com.example.library.dto;

import lombok.Data;
import java.util.Set;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String isbn;
    private Long authorId;
    private Set<Long> genreIds;

    public BookDto(String number, String number1, long l) {
    }

    public BookDto() {
        
    }

    public Object title() {
        return null;
    }
}