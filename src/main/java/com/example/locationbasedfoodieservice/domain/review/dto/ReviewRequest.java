package com.example.locationbasedfoodieservice.domain.review.dto;

import com.example.locationbasedfoodieservice.domain.hotel.Hotel;
import com.example.locationbasedfoodieservice.domain.review.Review;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReviewRequest {
    @NotNull(message = "평점은 공백일 수 없습니다")
    @Min(1)
    @Max(5)
    private Integer score;

    private String content;

    @NotNull
    private Long memberId;

    @NotNull
    private Long restaurantId;

    public Review toEntity(Hotel hotel) {
        return Review.builder()
                .score(score)
                .content(content)
                .restaurant(hotel)
                .build();
    }

}
