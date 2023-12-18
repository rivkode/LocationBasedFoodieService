package com.example.locationbasedfoodieservice.domain.hotel.service;

import com.example.locationbasedfoodieservice.domain.hotel.Hotel;
import com.example.locationbasedfoodieservice.domain.hotel.dto.HotelResponseDto;
import com.example.locationbasedfoodieservice.domain.hotel.repository.HotelRepository;
import com.example.locationbasedfoodieservice.domain.hotel.scheduler.RawHotelScheduler;
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

    /**
     * 위도, 경도 기준 반경 5KM 이내에 존재하는 호텔 리스트 반환
     * @param inputLatitude
     * @param inputLongitude
     * @return
     */
    public List<HotelResponseDto> getHotelsInSquare(Double inputLatitude, Double inputLongitude) {
        Double radius = 5.0; // 5km 반경

        Double leftLongitude = inputLongitude - radius / (111.32 * Math.cos(Math.toRadians(inputLatitude)));
        Double rightLongitude = inputLongitude + radius / (111.32 * Math.cos(Math.toRadians(inputLatitude)));
        Double lowerLatitude = inputLatitude - (radius / 111.32);
        Double upperLatitude = inputLatitude + (radius / 111.32);

        List<Hotel> hotels =  hotelRepository.findHotelsInSquare(leftLongitude, rightLongitude, lowerLatitude, upperLatitude);
        return HotelResponseDto.from(hotels);
    }
}
