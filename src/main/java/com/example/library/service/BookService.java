package com.example.library.service;

import com.example.library.dto.BookDto;
import com.example.library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();
    BookDto getBookById(Long id);
    BookDto createBook(BookDto bookDto);
    BookDto updateBook(Long id, BookDto bookDto);
    void deleteBook(Long id);

    PageImpl<Book> getAllBooks(Pageable pageable);
    Page<BookDto> searchBooks(String title, Pageable pageable);
    Page<BookDto> getBooksByAuthor(Long authorId, Pageable pageable);
    Page<BookDto> getBooksByGenre(String genre, Pageable pageable);
}