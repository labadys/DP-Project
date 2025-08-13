package com.example.library.controller;

import org.springframework.data.domain.Pageable;
import com.example.library.dto.BookDto;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public  Page<BookDto> getAllBooks(Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }
}