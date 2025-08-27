package com.example.library.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class PublisherRequestDto {
    @NotBlank(message = "Publisher name is required")
    private String name;

    private String address;
    private String contactInfo;
}