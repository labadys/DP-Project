package com.example.library.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;
    private Long bookId;       // ID книги
    private Long userId;       // ID пользователя
    private LocalDateTime orderDate;
    private LocalDateTime dueDate;
    private String status;
}