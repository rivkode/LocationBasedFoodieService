package com.example.locationbasedfoodieservice.domain.hotel.controller;

import com.example.locationbasedfoodieservice.domain.hotel.dto.HotelResponseDto;
import com.example.locationbasedfoodieservice.domain.hotel.service.HotelService;
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
public class HotelController {

    private final HotelService hotelService;

    @GetMapping(value = "/{restaurantId}")
    public ResponseEntity<HotelResponseDto> getHotel(
            @PathVariable("restaurantId") Long restaurantId) {

        return ResponseEntity.ok().body(hotelService.getHotel(restaurantId));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<HotelResponseDto>> getListHotel(
            @RequestParam(value = "latitude") Double latitude,
            @RequestParam(value = "longitude") Double longitude
    ) {
        return ResponseEntity.ok().body(hotelService.getListHotel(latitude, longitude));
    }
}
