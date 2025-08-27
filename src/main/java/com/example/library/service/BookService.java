package com.example.library.service;

import com.example.library.dto.BookDto;
import com.example.library.dto.BookRequestDto;
import org.hamcrest.Matcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();
    BookDto getBookById(Long id);
    BookDto saveBook(BookDto bookDto); // Этот метод отсутствует в реализации
    BookDto updateBook(Long id, BookDto bookDto);

    BookDto createBook(BookRequestDto request);

    BookDto updateBook(Long id, BookRequestDto request);

    void deleteBook(Long id);

    Page<BookDto> getAllBooks(Pageable pageable);

    Object createBook(Matcher<BookDto> any);
}