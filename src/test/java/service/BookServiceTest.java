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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
}