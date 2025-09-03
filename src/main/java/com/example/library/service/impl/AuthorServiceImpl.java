package com.example.library.service.impl;

import com.example.library.dto.AuthorDto;
import com.example.library.entity.Author;
import com.example.library.mapper.AuthorMapper;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDto)
                .toList();
    }

    @Override
    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        return authorMapper.toDto(author);
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorRequest) {
        Author author = authorMapper.toEntity(authorRequest);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toDto(savedAuthor);
    }

    @Override
    public AuthorDto updateAuthor(Long id, AuthorDto authorRequest) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        existingAuthor.setName(authorRequest.getName());
        existingAuthor.setBiography(authorRequest.getBiography());

        Author updatedAuthor = authorRepository.save(existingAuthor);
        return authorMapper.toDto(updatedAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}