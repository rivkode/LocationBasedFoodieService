package com.example.locationbasedfoodieservice.domain.restaurant.service;

import com.example.locationbasedfoodieservice.domain.restaurant.Restaurant;
import com.example.locationbasedfoodieservice.domain.restaurant.dto.RestaurantResponseDto;
import com.example.locationbasedfoodieservice.domain.restaurant.repository.RestaurantRepository;
import com.example.locationbasedfoodieservice.system.exception.CustomErrorCode;
import com.example.locationbasedfoodieservice.system.exception.CustomException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantResponseDto getRestaurant(Long restaurantId) {
        Restaurant restaurant = findRestaurant(restaurantId);

        return RestaurantResponseDto.from(restaurant);
    }

    public Restaurant findRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.RESTAURANT_NOT_FOUND));
    }

    public List<RestaurantResponseDto> getListRestaurant(Double latitude, Double longitude) {
        List<Restaurant> restaurants = restaurantRepository.findByLocation(latitude, longitude);

        return RestaurantResponseDto.from(restaurants);
    }
}
