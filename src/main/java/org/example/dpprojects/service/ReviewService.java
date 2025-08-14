package org.example.dpprojects.service;

import org.example.dpprojects.dto.ReviewDto;
import org.example.dpprojects.dto.ReviewRequestDto;
import java.util.List;

public interface ReviewService {
    ReviewDto addReview(ReviewRequestDto reviewRequest);
    List<ReviewDto> getReviewsByBookId(Long bookId);
}