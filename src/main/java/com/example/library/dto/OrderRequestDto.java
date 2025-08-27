package com.example.library.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderRequestDto {
    private Long bookId;
    private LocalDateTime orderDate;
    private LocalDateTime dueDate;
    private String status;
}