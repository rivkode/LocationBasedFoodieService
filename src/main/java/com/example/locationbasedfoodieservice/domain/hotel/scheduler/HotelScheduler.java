package com.example.locationbasedfoodieservice.domain.hotel.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;


public class HotelScheduler {
    @Value("${api.key}")
    private String apiKey;

    /**
     * 크론 스케줄링
     * 첫 번째 필드: 초 (0-59)
     * 두 번째 필드: 분 (0-59)
     * 세 번째 필드: 시간 (0-23)
     * 네 번째 필드: 일 (1-31)
     * 다섯 번째 필드: 월 (1-12)
     * 여섯 번째 필드: 요일 (0-6, 일요일부터 토요일까지, 일요일=0 또는 7)
     */

    // 데이터 총 개수를 가져와서 dataCount에 넣어줍니다.
    @Scheduled(cron = "0 0 4 * * 6")
    private void updateHotel() {

    }
}
