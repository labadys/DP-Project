package com.example.library.service.impl;

import com.example.library.dto.BookDto;
import com.example.library.dto.BookRequestDto;
import com.example.library.entity.Book;
import com.example.library.entity.Author;
import com.example.library.entity.Genre;
import com.example.library.exception.AuthorNotFoundException;
import com.example.library.exception.BookNotFoundException;
import com.example.library.exception.GenreNotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.BookRepository;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.GenreRepository;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public BookDto findById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    /**
     * @return
     */
    @Override
    public Page<BookDto> findAll() {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toDto);
    }

    @Override
    @Transactional
    public BookDto create(BookRequestDto request) {
        Book book = bookMapper.toEntity(request);

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(request.getAuthorId()));
        book.setAuthor(author);

        Set<Genre> genres = new HashSet<>(genreRepository.findAllById(request.getGenreIds()));
        if (genres.size() != request.getGenreIds().size()) {
            throw new GenreNotFoundException("Some genres not found");
        }
        book.setGenres(genres);

        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Override
    @Transactional
    public BookDto update(Long id, BookRequestDto request) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookMapper.updateEntity(request, existingBook);

        if (!existingBook.getAuthor().getId().equals(request.getAuthorId())) {
            Author author = authorRepository.findById(request.getAuthorId())
                    .orElseThrow(() -> new AuthorNotFoundException(request.getAuthorId()));
            existingBook.setAuthor(author);
        }

        Set<Genre> genres = new HashSet<>(genreRepository.findAllById(request.getGenreIds()));
        if (genres.size() != request.getGenreIds().size()) {
            throw new GenreNotFoundException("Some genres not found");
        }
        existingBook.setGenres(genres);

        return bookMapper.toDto(bookRepository.save(existingBook));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        bookRepository.delete(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId).stream()
                .map((Object book) -> bookMapper.toDto((Book) book))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title).stream()
                .map((Object book) -> bookMapper.toDto((Book) book))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookDto save(BookDto bookDto) {
        BookRequestDto request = new BookRequestDto();
        request.setTitle(bookDto.getTitle());
        request.setIsbn(bookDto.getIsbn());
        request.setAuthorId(bookDto.getAuthorId());
        request.setGenreIds(bookDto.getGenreIds());

        return create(request);
    }
}