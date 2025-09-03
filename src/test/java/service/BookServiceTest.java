package service;

import com.example.library.dto.BookDto;
import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void whenSaveBook_thenReturnSavedBook() {
        BookDto bookDto = new BookDto(1L, "Test Book", 2023, "Test Publisher");
        Book book = new Book(1L, "Test Book", 2023, "Test Publisher");

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookDto savedBook = bookService.createBook(bookDto);

        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo(bookDto.getTitle());
    }

    @Test
    void getAllBooks_withPagination_shouldReturnPaginatedResults() {
        List<Book> books = Arrays.asList(
                new Book(1L, "Book 1", 2023, "Publisher 1"),
                new Book(2L, "Book 2", 2023, "Publisher 2")
        );

        Pageable pageable = PageRequest.of(0, 10);
        Page<Book> expectedPage = new PageImpl<>(books, pageable, books.size());

        when(bookRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<Book> result = bookService.getAllBooks(pageable);

        assertThat(result).isEqualTo(expectedPage);
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getNumber()).isEqualTo(0);
        assertThat(result.getTotalElements()).isEqualTo(2);
        verify(bookRepository).findAll(pageable);
    }

    @Test
    void getAllBooks_withSecondPage_shouldReturnCorrectPage() {

        Pageable pageable = PageRequest.of(1, 5);
        Page<Book> expectedPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

        when(bookRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<Book> result = bookService.getAllBooks(pageable);

        assertThat(result.getNumber()).isEqualTo(1);
        assertThat(result.getContent()).isEmpty();
        assertThat(result.getTotalElements()).isEqualTo(0);
        verify(bookRepository).findAll(pageable);
    }

    @Test
    void getAllBooks_withEmptyDatabase_shouldReturnEmptyPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Book> emptyPage = Page.empty(pageable);

        when(bookRepository.findAll(pageable)).thenReturn(emptyPage);

        Page<Book> result = bookService.getAllBooks(pageable);

        assertThat(result.isEmpty()).isTrue();
        assertThat(result.getContent()).isEmpty();
        assertThat(result.getTotalElements()).isEqualTo(0);
        verify(bookRepository).findAll(pageable);
    }

    @Test
    void getAllBooks_withSorting_shouldReturnSortedResults() {
        List<Book> books = Arrays.asList(
                new Book(1L, "A Book", 2023, "Publisher A"),
                new Book(2L, "B Book", 2023, "Publisher B")
        );

        Pageable pageable = PageRequest.of(0, 10, Sort.by("title").ascending());
        Page<Book> expectedPage = new PageImpl<>(books, pageable, books.size());

        when(bookRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<Book> result = bookService.getAllBooks(pageable);

        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("A Book");
        assertThat(result.getContent().get(1).getTitle()).isEqualTo("B Book");
        verify(bookRepository).findAll(pageable);
    }

    @Test
    void getAllBooks_withCustomPageSize_shouldReturnCorrectNumberOfItems() {
        List<Book> books = Arrays.asList(
                new Book(1L, "Book 1", 2023, "Publisher 1"),
                new Book(2L, "Book 2", 2023, "Publisher 2")
        );

        Pageable pageable = PageRequest.of(0, 2);
        Page<Book> expectedPage = new PageImpl<>(books, pageable, 10);

        when(bookRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<Book> result = bookService.getAllBooks(pageable);

        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getSize()).isEqualTo(2);
        assertThat(result.getTotalElements()).isEqualTo(10);
        assertThat(result.getTotalPages()).isEqualTo(5);
        verify(bookRepository).findAll(pageable);
    }
}