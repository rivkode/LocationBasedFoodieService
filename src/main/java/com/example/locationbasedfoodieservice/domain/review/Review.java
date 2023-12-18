package com.example.locationbasedfoodieservice.domain.review;

import com.example.locationbasedfoodieservice.domain.hotel.Hotel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Hotel hotel;

    @Builder
    public Review(Long id, Integer score, String content, Hotel hotel) {
        this.id = id;
        this.score = score;
        this.content = content;
        this.hotel = hotel;
    }

    public void addRestaurant(Hotel hotel) {
        this.hotel = hotel;
        hotel.getReviews().add(this);
    }

}
