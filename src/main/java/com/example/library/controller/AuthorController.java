package com.example.library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Authors API", description = "API для управления авторами")
public class AuthorController {

    @GetMapping
    @Operation(summary = "Получить всех авторов", description = "Возвращает список всех авторов")
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        // Ваша логика
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить автора по ID", description = "Возвращает автора по указанному идентификатору")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id) {
        // Ваша логика
        return ResponseEntity.ok(new AuthorResponse(id, "Author Name"));
    }

    @PostMapping
    @Operation(summary = "Создать автора", description = "Создает нового автора")
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody AuthorRequest authorRequest) {
        // Ваша логика
        return ResponseEntity.ok(new AuthorResponse(1L, authorRequest.name()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить автора", description = "Обновляет данные автора")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequest authorRequest) {
        // Ваша логика
        return ResponseEntity.ok(new AuthorResponse(id, authorRequest.name()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить автора", description = "Удаляет автора по ID")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        // Ваша логика
        return ResponseEntity.noContent().build();
    }

    // DTO классы
    public record AuthorRequest(String name, String biography) {}
    public record AuthorResponse(Long id, String name) {}
}