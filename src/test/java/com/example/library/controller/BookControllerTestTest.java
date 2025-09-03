package com.example.library.controller;

import com.example.library.dto.BookDto;
import com.example.library.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Test
    void getAllBooks_ShouldReturnBooks() throws Exception {
        BookDto book1 = new BookDto();
        book1.setId(1L);
        book1.setTitle("Test Book 1");
        book1.setAuthor("Author 1");
        book1.setPublicationYear(2020);
        book1.setIsbn("1234567890");
        book1.setPublisher("Publisher 1");
        book1.setGenre("Fiction");

        BookDto book2 = new BookDto();
        book2.setId(2L);
        book2.setTitle("Test Book 2");
        book2.setAuthor("Author 2");
        book2.setPublicationYear(2021);
        book2.setIsbn("0987654321");
        book2.setPublisher("Publisher 2");
        book2.setGenre("Science");

        when(bookService.getAllBooks()).thenReturn(List.of(book1, book2));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Test Book 1"))
                .andExpect(jsonPath("$[0].author").value("Author 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].title").value("Test Book 2"))
                .andExpect(jsonPath("$[1].author").value("Author 2"));

        verify(bookService).getAllBooks();
    }

    @Test
    void getBookById_WhenBookExists_ShouldReturnBook() throws Exception {
        Long bookId = 1L;
        BookDto bookDto = new BookDto();
        bookDto.setId(bookId);
        bookDto.setTitle("Test Book");
        bookDto.setAuthor("Test Author");
        bookDto.setPublicationYear(2024);
        bookDto.setIsbn("1234567890");
        bookDto.setPublisher("Test Publisher");
        bookDto.setGenre("Test Genre");

        when(bookService.getBookById(bookId)).thenReturn(bookDto);

        mockMvc.perform(get("/api/books/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookId))
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"))
                .andExpect(jsonPath("$.publicationYear").value(2024));

        verify(bookService).getBookById(bookId);
    }

    @Test
    void createBook_ShouldReturnCreatedBook() throws Exception {
        BookDto inputDto = new BookDto();
        inputDto.setTitle("New Book");
        inputDto.setAuthor("New Author");
        inputDto.setPublicationYear(2024);
        inputDto.setIsbn("0987654321");
        inputDto.setPublisher("New Publisher");
        inputDto.setGenre("New Genre");

        BookDto createdDto = new BookDto();
        createdDto.setId(1L);
        createdDto.setTitle("New Book");
        createdDto.setAuthor("New Author");
        createdDto.setPublicationYear(2024);
        createdDto.setIsbn("0987654321");
        createdDto.setPublisher("New Publisher");
        createdDto.setGenre("New Genre");

        when(bookService.createBook(any(BookDto.class))).thenReturn(createdDto);

        String bookJson = objectMapper.writeValueAsString(inputDto);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("New Book"))
                .andExpect(jsonPath("$.author").value("New Author"));

        verify(bookService).createBook(any(BookDto.class));
    }

    @Test
    void updateBook_ShouldReturnUpdatedBook() throws Exception {
        Long bookId = 1L;
        BookDto updateDto = new BookDto();
        updateDto.setTitle("Updated Book");
        updateDto.setAuthor("Updated Author");
        updateDto.setPublicationYear(2024);
        updateDto.setIsbn("0987654321");
        updateDto.setPublisher("Updated Publisher");
        updateDto.setGenre("Updated Genre");

        BookDto updatedDto = new BookDto();
        updatedDto.setId(bookId);
        updatedDto.setTitle("Updated Book");
        updatedDto.setAuthor("Updated Author");
        updatedDto.setPublicationYear(2024);
        updatedDto.setIsbn("0987654321");
        updatedDto.setPublisher("Updated Publisher");
        updatedDto.setGenre("Updated Genre");

        when(bookService.updateBook(eq(bookId), any(BookDto.class))).thenReturn(updatedDto);

        String bookJson = objectMapper.writeValueAsString(updateDto);

        mockMvc.perform(put("/api/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookId))
                .andExpect(jsonPath("$.title").value("Updated Book"))
                .andExpect(jsonPath("$.author").value("Updated Author"));

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