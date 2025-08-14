package org.example.dpprojects.controller;

import org.example.dpprojects.dto.ReviewDto;
import org.example.dpprojects.dto.ReviewRequestDto;
import org.example.dpprojects.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewDto> addReview(@RequestBody ReviewRequestDto reviewRequest) {
        ReviewDto review = reviewService.addReview(reviewRequest);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByBook(@PathVariable Long bookId) {
        List<ReviewDto> reviews = reviewService.getReviewsByBookId(bookId);
        return ResponseEntity.ok(reviews);
    }
}