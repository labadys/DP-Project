package com.example.library.controller;

import com.example.library.dto.BookDto;
import com.example.library.dto.BookRequestDto;
import com.example.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@WithMockUser
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void getAllBooks_ShouldReturnBooks() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Test Book");
        bookDto.setAuthor("Test Author");
        bookDto.setPublicationYear(2024);
        bookDto.setIsbn("1234567890");
        bookDto.setPublisher("Test Publisher");

        when(bookService.getAllBooks()).thenReturn(List.of(bookDto));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Book"))
                .andExpect(jsonPath("$[0].author").value("Test Author"));

        verify(bookService).getAllBooks();
    }

    @Test
    void getBookById_WhenBookExists_ShouldReturnBook() throws Exception {
        // Arrange
        Long bookId = 1L;
        BookDto bookDto = new BookDto();
        bookDto.setId(bookId);
        bookDto.setTitle("Test Book");
        bookDto.setAuthor("Test Author");
        bookDto.setPublicationYear(2024);
        bookDto.setIsbn("1234567890");
        bookDto.setPublisher("Test Publisher");

        when(bookService.getBookById(bookId)).thenReturn(bookDto);

        mockMvc.perform(get("/api/books/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookId))
                .andExpect(jsonPath("$.title").value("Test Book"));

        verify(bookService).getBookById(bookId);
    }

    @Test
    void createBook_ShouldReturnCreatedBook() throws Exception {
        BookDto createdBookDto = new BookDto();
        createdBookDto.setId(1L);
        createdBookDto.setTitle("New Book");
        createdBookDto.setAuthor("New Author");
        createdBookDto.setPublicationYear(2024);
        createdBookDto.setIsbn("0987654321");
        createdBookDto.setPublisher("New Publisher");

        when(bookService.createBook(any(BookDto.class))).thenReturn(createdBookDto);

        String bookJson = """
        {
            "title": "New Book",
            "author": "New Author",
            "publicationYear": 2024,
            "isbn": "0987654321",
            "publisher": "New Publisher"
        }
        """;

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Book"))
                .andExpect(jsonPath("$.id").value(1L));

        verify(bookService).createBook(any(BookDto.class));
    }

    @Test
    void updateBook_ShouldReturnUpdatedBook() throws Exception {
        Long bookId = 1L;
        BookDto updatedBookDto = new BookDto();
        updatedBookDto.setId(bookId);
        updatedBookDto.setTitle("Updated Book");
        updatedBookDto.setAuthor("Updated Author");
        updatedBookDto.setPublicationYear(2024);
        updatedBookDto.setIsbn("0987654321");
        updatedBookDto.setPublisher("Updated Publisher");

        when(bookService.updateBook(eq(bookId), any(BookDto.class))).thenReturn(updatedBookDto);

        String bookJson = """
        {
            "title": "Updated Book",
            "author": "Updated Author",
            "publicationYear": 2024,
            "isbn": "0987654321",
            "publisher": "Updated Publisher"
        }
        """;

        mockMvc.perform(put("/api/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Book"))
                .andExpect(jsonPath("$.id").value(bookId));

        verify(bookService).updateBook(eq(bookId), any(BookDto.class));
    }

    @Test
    void deleteBook_ShouldReturnNoContent() throws Exception {
        Long bookId = 1L;
        doNothing().when(bookService).deleteBook(bookId);

        mockMvc.perform(delete("/api/books/{id}", bookId))
                .andExpect(status().isNoContent());

        verify(bookService).deleteBook(bookId);
    }
}