package com.example.library.service;

import com.example.library.dto.AuthorDto;
import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAllAuthors();
    AuthorDto getAuthorById(Long id);
    AuthorDto createAuthor(AuthorDto authorRequest);
    AuthorDto updateAuthor(Long id, AuthorDto authorRequest);
    void deleteAuthor(Long id);
}