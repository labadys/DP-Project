package com.example.library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books API", description = "API для управления книгами")
public class BookController {

    @GetMapping
    @Operation(summary = "Получить все книги", description = "Возвращает список всех книг")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить книгу по ID", description = "Возвращает книгу по указанному идентификатору")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(new BookResponse(id, "Book Title", "Author Name"));
    }

    @PostMapping
    @Operation(summary = "Создать книгу", description = "Создает новую книгу")
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(new BookResponse(1L, bookRequest.title(), bookRequest.author()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить книгу", description = "Обновляет данные книги")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(new BookResponse(id, bookRequest.title(), bookRequest.author()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить книгу", description = "Удаляет книгу по ID")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

    public record BookRequest(String title, String author, String isbn, Integer year) {}
    public record BookResponse(Long id, String title, String author) {}
}