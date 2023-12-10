package com.example.locationbasedfoodieservice.domain.restaurant.controller;

import com.example.locationbasedfoodieservice.domain.restaurant.dto.RestaurantResponseDto;
import com.example.locationbasedfoodieservice.domain.restaurant.service.RestaurantService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping(value = "/{restaurantId}")
    public ResponseEntity<RestaurantResponseDto> getRestaurant(
            @PathVariable("restaurantId") Long restaurantId) {

        return ResponseEntity.ok().body(restaurantService.getRestaurant(restaurantId));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<RestaurantResponseDto>> getListRestaurant(
            @RequestParam(value = "latitude") Double latitude,
            @RequestParam(value = "longitude") Double longitude
    ) {
        return ResponseEntity.ok().body(restaurantService.getListRestaurant(latitude, longitude));
    }
}
