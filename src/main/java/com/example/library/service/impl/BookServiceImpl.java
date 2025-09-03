package com.example.library.service.impl;

import com.example.library.dto.BookDto;
import com.example.library.entity.Book;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

        existingBook.setTitle(bookDto.getTitle());
        existingBook.setPublicationYear(bookDto.getPublicationYear());
        existingBook.setIsbn(bookDto.getIsbn());

        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDto(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public PageImpl<Book> getAllBooks(Pageable pageable) {
        // TODO:  пагинациЯ вместо возврата всех книг

        List<Book> allBooks = bookRepository.findAll();
        return new PageImpl<>(allBooks, pageable, allBooks.size());
    }

    @Override
    public Page<BookDto> searchBooks(String title, Pageable pageable) {
        // TODO:  поиск с пагинацией
        throw new UnsupportedOperationException("Search with pagination not implemented yet");
    }

    @Override
    public Page<BookDto> getBooksByAuthor(Long authorId, Pageable pageable) {
        // TODO: фильтрацию с пагинацией
        throw new UnsupportedOperationException("Filter by author with pagination not implemented yet");
    }

    @Override
    public Page<BookDto> getBooksByGenre(String genre, Pageable pageable) {
        // TODO:  фильтрацию с пагинацией
        throw new UnsupportedOperationException("Filter by genre with pagination not implemented yet");
    }
}