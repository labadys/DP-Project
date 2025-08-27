package com.example.library.controller;

import com.example.library.dto.BookDto;
import com.example.library.dto.BookRequestDto;
import com.example.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void getAllBooks_ShouldReturnBooks() throws Exception {
        // Arrange
        BookDto bookDto = new BookDto(1L, "Test Book", "Test Author", 2024, "1234567890", "Test Publisher");
        when(bookService.getAllBooks()).thenReturn(List.of(bookDto)); // Возвращаем BookDto, а не Book entity

        // Act & Assert
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
        BookDto bookDto = new BookDto(bookId, "Test Book", "Test Author", 2024, "1234567890", "Test Publisher");
        when(bookService.getBookById(bookId)).thenReturn(bookDto); // Возвращаем BookDto

        // Act & Assert
        mockMvc.perform(get("/api/books/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookId))
                .andExpect(jsonPath("$.title").value("Test Book"));

        verify(bookService).getBookById(bookId);
    }

    @Test
    void createBook_ShouldReturnCreatedBook() throws Exception {
        // Arrange
        BookDto bookDto = new BookDto(1L, "New Book", "New Author", 2024, "0987654321", "New Publisher");
        when(bookService.createBook((BookRequestDto) any(BookDto.class))).thenReturn(bookDto); // Возвращаем BookDto

        String bookJson = """
        {
            "title": "New Book",
            "author": "New Author",
            "publicationYear": 2024,
            "isbn": "0987654321",
            "publisher": "New Publisher"
        }
        """;

        // Act & Assert
        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Book"));

        verify(bookService).createBook((BookRequestDto) any(BookDto.class));
    }

    @Test
    void deleteBook_ShouldReturnNoContent() throws Exception {
        // Arrange
        Long bookId = 1L;
        doNothing().when(bookService).deleteBook(bookId);

        // Act & Assert
        mockMvc.perform(delete("/api/books/{id}", bookId))
                .andExpect(status().isNoContent());

        verify(bookService).deleteBook(bookId);
    }
}