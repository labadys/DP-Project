package com.example.library.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDto {
    private Long id;

    @NotNull
    private Long bookId;

    @NotNull
    private Long userId;

    @NotNull
    private Integer quantity;

    private LocalDate orderDate;
}
