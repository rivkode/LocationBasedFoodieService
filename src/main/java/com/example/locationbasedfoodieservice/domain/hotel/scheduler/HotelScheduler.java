package com.example.locationbasedfoodieservice.domain.hotel.scheduler;

import com.example.locationbasedfoodieservice.domain.hotel.Hotel;
import com.example.locationbasedfoodieservice.domain.hotel.RawHotel;
import com.example.locationbasedfoodieservice.domain.hotel.repository.HotelRepository;
import com.example.locationbasedfoodieservice.domain.hotel.repository.RawHotelRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HotelScheduler {

    private final HotelRepository hotelRepository;
    private final RawHotelRepository rawHotelRepository;

    /**
     * 크론 스케줄링
     * 첫 번째 필드: 초 (0-59)
     * 두 번째 필드: 분 (0-59)
     * 세 번째 필드: 시간 (0-23)
     * 네 번째 필드: 일 (1-31)
     * 다섯 번째 필드: 월 (1-12)
     * 여섯 번째 필드: 요일 (0-6, 일요일부터 토요일까지, 일요일=0 또는 7)
     * 월요일 06:05
     */
    @Scheduled(cron = "0 5 6 * * 1")
    public void updateRestaurant() {
        log.info("Data PreProcessing Start");

        List<RawHotel> rawHotels = rawHotelRepository.findAll();
        Map<String, Hotel> hotels = new HashMap<>();

        for (RawHotel rawHotel : rawHotels) {
            String name = rawHotel.getBizplcNm();
            String address = rawHotel.getRefineRoadnmAddr();
            String nameAddress = name + address;
            String uniqueKey = nameAddress.replaceAll(" ", "");

            Hotel hotel = hotelRepository.findByNameAddress(uniqueKey)
                    .orElse(null);

            if (hotel == null) {
                hotels.put(uniqueKey, rawHotel.toRestaurant(uniqueKey));
                continue;
            }

            hotel.update(rawHotel);
        }

        hotelRepository.saveAll(hotels.values());

        log.info("Data Processing End");
    }

}
