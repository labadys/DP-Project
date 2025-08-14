package com.example.library.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class OrderDto {
    private Long id;
    private LocalDate orderDate;
    private Long userId;
    private Long bookId;
    private int quantity;
}
