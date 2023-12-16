package com.example.locationbasedfoodieservice.domain.review.service;

import com.example.locationbasedfoodieservice.domain.hotel.Hotel;
import com.example.locationbasedfoodieservice.domain.hotel.service.HotelService;
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
    private final HotelService hotelService;

    public void createReview(ReviewRequest request) {
        Hotel hotel = hotelService.findHotel(request.getRestaurantId());

        Review savedReview = reviewRepository.save(request.toEntity(hotel));
        savedReview.addRestaurant(hotel);

        int totalReviewNum = reviewRepository.findCountByRestaurantId(request.getRestaurantId());
        int reviewScore = request.getScore();

        checkAndUpdateRating(hotel, totalReviewNum, reviewScore);

    }

    // todo
    // 평균 * 총 개수
    // 새점수 + 총점
    // 평균 계산
    // 리뷰 평점 업데이트
    private void updateRestaurantRating(Hotel hotel, int totalReviewNum,
            int reviewScore, double avg) {

        double calAvg = avg * totalReviewNum;
        double newCalAvg = calAvg + reviewScore;
        double resultAvg = newCalAvg / (totalReviewNum + 1);
        hotel.updateRating(resultAvg);
    }

    private void checkAndUpdateRating(Hotel hotel, int totalReviewNum, int reviewScore) {

        if (hotel.getRating() != null) {
            double avg = hotel.getRating();
            this.updateRestaurantRating(hotel, totalReviewNum, reviewScore, avg);
        } else {
            hotel.updateRating(reviewScore);
        }
    }

}
