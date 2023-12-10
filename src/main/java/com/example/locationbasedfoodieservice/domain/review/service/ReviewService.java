package com.example.locationbasedfoodieservice.domain.review.service;

import com.example.locationbasedfoodieservice.domain.restaurant.Restaurant;
import com.example.locationbasedfoodieservice.domain.restaurant.service.RestaurantService;
import com.example.locationbasedfoodieservice.domain.review.Review;
import com.example.locationbasedfoodieservice.domain.review.dto.ReviewRequest;
import com.example.locationbasedfoodieservice.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantService restaurantService;

    public void createReview(ReviewRequest request) {
        Restaurant restaurant = restaurantService.findRestaurant(request.getRestaurantId());

        Review savedReview = reviewRepository.save(request.toEntity(restaurant));
        savedReview.addRestaurant(restaurant);

        int totalReviewNum = reviewRepository.findCountByRestaurantId(request.getRestaurantId());
        int reviewScore = request.getScore();

        checkAndUpdateRating(restaurant, totalReviewNum, reviewScore);

    }

    // todo
    // 평균 * 총 개수
    // 새점수 + 총점
    // 평균 계산
    // 리뷰 평점 업데이트
    private void updateRestaurantRating(Restaurant restaurant, int totalReviewNum,
            int reviewScore, double avg) {

        double calAvg = avg * totalReviewNum;
        double newCalAvg = calAvg + reviewScore;
        double resultAvg = newCalAvg / (totalReviewNum + 1);
        restaurant.updateRating(resultAvg);
    }

    private void checkAndUpdateRating(Restaurant restaurant, int totalReviewNum, int reviewScore) {

        if (restaurant.getRating() != null) {
            double avg = restaurant.getRating();
            this.updateRestaurantRating(restaurant, totalReviewNum, reviewScore, avg);
        } else {
            restaurant.updateRating(reviewScore);
        }
    }

}
