package com.example.locationbasedfoodieservice.domain.hotel.service;

import com.example.locationbasedfoodieservice.domain.hotel.Hotel;
import com.example.locationbasedfoodieservice.domain.hotel.dto.HotelResponseDto;
import com.example.locationbasedfoodieservice.domain.hotel.repository.HotelRepository;
import com.example.locationbasedfoodieservice.system.exception.CustomErrorCode;
import com.example.locationbasedfoodieservice.system.exception.CustomException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelResponseDto getHotel(Long restaurantId) {
        Hotel hotel = findHotel(restaurantId);

        return HotelResponseDto.from(hotel);
    }

    public Hotel findHotel(Long restaurantId) {
        return hotelRepository.findById(restaurantId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.RESTAURANT_NOT_FOUND));
    }

    public List<HotelResponseDto> getListHotel(Double latitude, Double longitude) {
        List<Hotel> hotels = hotelRepository.findByLocation(latitude, longitude);

        return HotelResponseDto.from(hotels);
    }
}
