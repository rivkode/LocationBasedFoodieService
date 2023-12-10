package com.example.locationbasedfoodieservice.domain.review.controller;

import com.example.locationbasedfoodieservice.domain.review.dto.ReviewRequest;
import com.example.locationbasedfoodieservice.domain.review.dto.ReviewResponseDto;
import com.example.locationbasedfoodieservice.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Void> createReview(@RequestBody ReviewRequest request) {
        reviewService.createReview(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
