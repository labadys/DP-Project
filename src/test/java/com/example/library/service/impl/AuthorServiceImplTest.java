package com.example.library.service.impl;

import com.example.library.dto.AuthorDto;
import com.example.library.entity.Author;
import com.example.library.mapper.AuthorMapper;
import com.example.library.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    void getAllAuthors_ShouldReturnListOfAuthors() {
        Author author1 = new Author();
        author1.setId(1L);
        author1.setName("John Doe");
        author1.setBiography("Famous writer");

        Author author2 = new Author();
        author2.setId(2L);
        author2.setName("Jane Smith");
        author2.setBiography("Best-selling author");

        AuthorDto authorDto1 = new AuthorDto();
        authorDto1.setId(1L);
        authorDto1.setName("John Doe");
        authorDto1.setBiography("Famous writer");

        AuthorDto authorDto2 = new AuthorDto();
        authorDto2.setId(2L);
        authorDto2.setName("Jane Smith");
        authorDto2.setBiography("Best-selling author");

        when(authorRepository.findAll()).thenReturn(List.of(author1, author2));
        when(authorMapper.toDto(author1)).thenReturn(authorDto1);
        when(authorMapper.toDto(author2)).thenReturn(authorDto2);

        List<AuthorDto> result = authorService.getAllAuthors();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());
        assertEquals("Famous writer", result.get(0).getBiography());

        verify(authorRepository).findAll();
        verify(authorMapper, times(2)).toDto(any(Author.class));
    }

    @Test
    void getAuthorById_WhenAuthorExists_ShouldReturnAuthor() {
        Long authorId = 1L;
        Author author = new Author();
        author.setId(authorId);
        author.setName("John Doe");
        author.setBiography("Famous writer");

        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(authorId);
        authorDto.setName("John Doe");
        authorDto.setBiography("Famous writer");

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
        when(authorMapper.toDto(author)).thenReturn(authorDto);

        AuthorDto result = authorService.getAuthorById(authorId);

        assertNotNull(result);
        assertEquals(authorId, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("Famous writer", result.getBiography());

        verify(authorRepository).findById(authorId);
        verify(authorMapper).toDto(author);
    }

    @Test
    void getAuthorById_WhenAuthorNotExists_ShouldThrowException() {
        Long nonExistentId = 999L;
        when(authorRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authorService.getAuthorById(nonExistentId);
        });

        assertEquals("Author not found", exception.getMessage());
        verify(authorRepository).findById(nonExistentId);
        verify(authorMapper, never()).toDto(any(Author.class));
    }

    @Test
    void createAuthor_ShouldSaveAndReturnAuthor() {
        AuthorDto inputDto = new AuthorDto();
        inputDto.setName("New Author");
        inputDto.setBiography("New biography");

        Author authorToSave = new Author();
        authorToSave.setName("New Author");
        authorToSave.setBiography("New biography");

        Author savedAuthor = new Author();
        savedAuthor.setId(1L);
        savedAuthor.setName("New Author");
        savedAuthor.setBiography("New biography");

        AuthorDto expectedDto = new AuthorDto();
        expectedDto.setId(1L);
        expectedDto.setName("New Author");
        expectedDto.setBiography("New biography");

        when(authorMapper.toEntity(inputDto)).thenReturn(authorToSave);
        when(authorRepository.save(authorToSave)).thenReturn(savedAuthor);
        when(authorMapper.toDto(savedAuthor)).thenReturn(expectedDto);

        AuthorDto result = authorService.createAuthor(inputDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New Author", result.getName());
        assertEquals("New biography", result.getBiography());

        verify(authorMapper).toEntity(inputDto);
        verify(authorRepository).save(authorToSave);
        verify(authorMapper).toDto(savedAuthor);
    }

    @Test
    void updateAuthor_ShouldUpdateAndReturnAuthor() {
        Long authorId = 1L;
        AuthorDto updateDto = new AuthorDto();
        updateDto.setName("Updated Author");
        updateDto.setBiography("Updated biography");

        Author existingAuthor = new Author();
        existingAuthor.setId(authorId);
        existingAuthor.setName("Old Author");
        existingAuthor.setBiography("Old biography");

        Author updatedAuthor = new Author();
        updatedAuthor.setId(authorId);
        updatedAuthor.setName("Updated Author");
        updatedAuthor.setBiography("Updated biography");

        AuthorDto expectedDto = new AuthorDto();
        expectedDto.setId(authorId);
        expectedDto.setName("Updated Author");
        expectedDto.setBiography("Updated biography");

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(existingAuthor));
        when(authorRepository.save(existingAuthor)).thenReturn(updatedAuthor);
        when(authorMapper.toDto(updatedAuthor)).thenReturn(expectedDto);

        AuthorDto result = authorService.updateAuthor(authorId, updateDto);

        assertNotNull(result);
        assertEquals(authorId, result.getId());
        assertEquals("Updated Author", result.getName());
        assertEquals("Updated biography", result.getBiography());

        assertEquals("Updated Author", existingAuthor.getName());
        assertEquals("Updated biography", existingAuthor.getBiography());

        verify(authorRepository).findById(authorId);
        verify(authorRepository).save(existingAuthor);
        verify(authorMapper).toDto(updatedAuthor);
    }

    @Test
    void updateAuthor_WhenAuthorNotExists_ShouldThrowException() {
        Long nonExistentId = 999L;
        AuthorDto updateDto = new AuthorDto();
        updateDto.setName("Updated Author");

        when(authorRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authorService.updateAuthor(nonExistentId, updateDto);
        });

        assertEquals("Author not found", exception.getMessage());
        verify(authorRepository).findById(nonExistentId);
        verify(authorRepository, never()).save(any(Author.class));
    }

    @Test
    void deleteAuthor_ShouldDeleteAuthor() {
        Long authorId = 1L;

        doNothing().when(authorRepository).deleteById(authorId);

        authorService.deleteAuthor(authorId);

        verify(authorRepository).deleteById(authorId);
    }
}