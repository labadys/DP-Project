package com.example.library;

import com.example.library.controller.AuthorController;
import com.example.library.dto.AuthorDto;
import com.example.library.dto.AuthorRequest;
import com.example.library.service.AuthorService;
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

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthorService authorService;

    @Test
    void getAllAuthors_ShouldReturnListOfAuthors() throws Exception {
        AuthorDto author1 = new AuthorDto();
        author1.setId(1L);
        author1.setName("John Doe");
        author1.setBiography("Famous writer");

        AuthorDto author2 = new AuthorDto();
        author2.setId(2L);
        author2.setName("Jane Smith");
        author2.setBiography("Best-selling author");

        when(authorService.getAllAuthors()).thenReturn(List.of(author1, author2));

        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"));

        verify(authorService).getAllAuthors();
    }

    @Test
    void getAuthorById_WhenAuthorExists_ShouldReturnAuthor() throws Exception {
        Long authorId = 1L;
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(authorId);
        authorDto.setName("John Doe");
        authorDto.setBiography("Famous writer");

        when(authorService.getAuthorById(authorId)).thenReturn(authorDto);

        mockMvc.perform(get("/api/authors/{id}", authorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(authorId))
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(authorService).getAuthorById(authorId);
    }

    @Test
    void createAuthor_ShouldReturnCreatedAuthor() throws Exception {
        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setName("New Author");
        authorRequest.setBiography("New biography");

        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(1L);
        authorDto.setName("New Author");
        authorDto.setBiography("New biography");

        when(authorService.createAuthor(any(AuthorDto.class))).thenReturn(authorDto);

        String requestJson = objectMapper.writeValueAsString(authorRequest);

        mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("New Author"));

        verify(authorService).createAuthor(any(AuthorDto.class));
    }

    @Test
    void updateAuthor_ShouldReturnUpdatedAuthor() throws Exception {
        Long authorId = 1L;
        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setName("Updated Author");
        authorRequest.setBiography("Updated biography");

        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(authorId);
        authorDto.setName("Updated Author");
        authorDto.setBiography("Updated biography");

        when(authorService.updateAuthor(eq(authorId), any(AuthorDto.class))).thenReturn(authorDto);

        String requestJson = objectMapper.writeValueAsString(authorRequest);

        mockMvc.perform(put("/api/authors/{id}", authorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(authorId))
                .andExpect(jsonPath("$.name").value("Updated Author"));

        verify(authorService).updateAuthor(eq(authorId), any(AuthorDto.class));
    }

    @Test
    void deleteAuthor_ShouldReturnNoContent() throws Exception {
        Long authorId = 1L;
        doNothing().when(authorService).deleteAuthor(authorId);

        mockMvc.perform(delete("/api/authors/{id}", authorId))
                .andExpect(status().isNoContent());

        verify(authorService).deleteAuthor(authorId);
    }
}