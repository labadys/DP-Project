package com.example.library.dto;

import lombok.Data;

@Data
public class OrderRequestDto {
    private Long userId;
    private Long bookId;
    private int quantity;
}
