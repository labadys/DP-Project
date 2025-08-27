package com.example.library.service.impl;

import com.example.library.dto.BookDto;
import com.example.library.dto.BookRequestDto;
import com.example.library.entity.Book;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * @param bookDto
     * @return
     */
    @Override
    public BookDto saveBook(BookDto bookDto) {
        return null;
    }

    /**
     * @param id
     * @param bookDto
     * @return
     */
    @Override
    public BookDto updateBook(Long id, BookDto bookDto) {
        return null;
    }

    @Override
    public BookDto createBook(BookRequestDto request) {
        Book book = bookMapper.toEntity(request);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Override
    public BookDto updateBook(Long id, BookRequestDto request) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Обновляем поля
        existingBook.setTitle(request.getTitle());
        existingBook.setPublicationYear(request.getPublicationYear());
        existingBook.setIsbn(request.getIsbn());

        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDto(updatedBook); // Добавлен return
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<BookDto> getAllBooks(Pageable pageable) {
        return null;
    }

    /**
     * @param any
     * @return
     */
    @Override
    public Object createBook(Matcher<BookDto> any) {
        return null;
    }
}