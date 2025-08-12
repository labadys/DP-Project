package com.example.library;

import com.example.library.dto.AuthorDto;
import com.example.library.dto.AuthorRequestDto;
import com.example.library.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
@Tag(name = "Authors", description = "API для управления авторами")
public class AuthorController {

    /**
     *
     */
    private final AtomicReference<AuthorService> authorService = new AtomicReference<AuthorService>();

    @GetMapping
    @Operation(summary = "Получить список авторов")
    public ResponseEntity<Page<AuthorDto>> getAllAuthors(Pageable pageable) {
        return ResponseEntity.ok(authorService.get().findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить автора по ID")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.get().findById(id));
    }

    @PostMapping
    @Operation(summary = "Создать нового автора")
    public ResponseEntity<AuthorDto> createAuthor(
            @Valid @RequestBody AuthorRequestDto authorRequest) {
        AuthorDto createdAuthor = authorService.get().create(authorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные автора")
    public ResponseEntity<AuthorDto> updateAuthor(
            @PathVariable Long id,
            @Valid @RequestBody AuthorRequestDto authorRequest) {
        return ResponseEntity.ok(authorService.get().update(id, authorRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить автора")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long id) {
        authorService.get().delete(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Поиск авторов по имени")
    public ResponseEntity<List<AuthorDto>> searchAuthors(
            @RequestParam String query) {
        return ResponseEntity.ok(authorService.get().searchAuthors(query));
    }
}