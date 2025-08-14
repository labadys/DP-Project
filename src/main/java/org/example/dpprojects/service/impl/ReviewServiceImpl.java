package org.example.dpprojects.service.impl;

import org.example.dpprojects.dto.ReviewDto;
import org.example.dpprojects.dto.ReviewRequestDto;
import org.example.dpprojects.entity.Review;
import org.example.dpprojects.mapper.ReviewMapper;
import org.example.dpprojects.repository.ReviewRepository;
import org.example.dpprojects.service.ReviewService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewDto addReview(ReviewRequestDto reviewRequest) {
        Review review = reviewMapper.toEntity(reviewRequest);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toDto(savedReview);
    }

    @Override
    public List<ReviewDto> getReviewsByBookId(Long bookId) {
        return reviewRepository.findByBookId(bookId).stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }
}
