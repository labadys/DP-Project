package com.example.library.service;

import com.example.library.dto.AuthorDto;
import com.example.library.dto.AuthorRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {
    Page<AuthorDto> findAll(Pageable pageable);
    AuthorDto findById(Long id);
    AuthorDto create(AuthorRequestDto authorRequest);
    AuthorDto update(Long id, AuthorRequestDto authorRequest);
    void delete(Long id);
    List<AuthorDto> searchAuthors(String query);
}