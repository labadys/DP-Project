package org.example.dpprojects.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private Long id;
    private String comment;
    private int rating;
    private Long bookId;
    private Long userId;
}
