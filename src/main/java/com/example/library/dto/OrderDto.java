package com.example.library.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;
    private Long bookId;
    private Long userId;
    private LocalDateTime orderDate;
    private LocalDateTime dueDate;
    private String status;
}