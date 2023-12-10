package com.example.locationbasedfoodieservice.domain.review.repository;

import com.example.locationbasedfoodieservice.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value =
            "SELECT COUNT(*) " +
            "FROM review " +
            "WHERE restaurant_id = :restaurantId", nativeQuery = true)
    int findCountByRestaurantId(@Param("restaurantId") Long restaurantId);
}
