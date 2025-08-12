package com.example.library.service;

import com.example.library.dto.BookDto;
import com.example.library.dto.BookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookService {
    BookDto findById(Long id);
    Page<BookDto> findAll();

    @Transactional(readOnly = true)
    Page<BookDto> findAll(Pageable pageable);

    BookDto create(BookRequestDto request);
    BookDto update(Long id, BookRequestDto request);
    void delete(Long id);
    List<BookDto> findByAuthorId(Long authorId);
    List<BookDto> searchByTitle(String title);

    BookDto save(BookDto bookDto);
}