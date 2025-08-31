package com.example.library.service.impl;

import com.example.library.dto.BookDto;
import com.example.library.entity.Book;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return bookMapper.toDto(book);
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Override
    public BookDto updateBook(Long id, BookDto bookDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Обновляем поля
        existingBook.setTitle(bookDto.getTitle());
        existingBook.setPublicationYear(bookDto.getPublicationYear());
        existingBook.setIsbn(bookDto.getIsbn());

        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDto(updatedBook); // ДОБАВЛЕН RETURN
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}