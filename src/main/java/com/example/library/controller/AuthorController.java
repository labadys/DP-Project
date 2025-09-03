package com.example.library.controller;

import com.example.library.dto.AuthorDto;
import com.example.library.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Authors API", description = "API для управления авторами")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    @Operation(summary = "Получить всех авторов", description = "Возвращает список всех авторов")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить автора по ID", description = "Возвращает автора по указанному идентификатору")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PostMapping
    @Operation(summary = "Создать автора", description = "Создает нового автора")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorRequest) {
        return ResponseEntity.ok(authorService.createAuthor(authorRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить автора", description = "Обновляет данные автора")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorRequest) {
        return ResponseEntity.ok(authorService.updateAuthor(id, authorRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить автора", description = "Удаляет автора по ID")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}