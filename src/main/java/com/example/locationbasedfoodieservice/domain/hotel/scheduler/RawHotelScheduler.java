package com.example.locationbasedfoodieservice.domain.hotel.scheduler;


import com.example.locationbasedfoodieservice.domain.hotel.RawHotel;
import com.example.locationbasedfoodieservice.domain.hotel.repository.RawHotelJdbcRepository;
import com.example.locationbasedfoodieservice.domain.hotel.repository.RawHotelRepository;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Slf4j
@Component
@RequiredArgsConstructor
public class RawHotelScheduler {
    private final RestTemplate restTemplate;
    private final RawHotelRepository rawHotelRepository;

    private final RawHotelJdbcRepository rawHotelJdbcRepository;
    @Value("${api.key}")
    private String apiKey;
    private Long numberOfData;
    private final Long BATCH_SIZE = 999L;    // 한 번의 API 요청에 999개까지의 데이터만을 받아올 수 있습니다.

    /**
     * 크론 스케줄링
     * 첫 번째 필드: 초 (0-59)
     * 두 번째 필드: 분 (0-59)
     * 세 번째 필드: 시간 (0-23)
     * 네 번째 필드: 일 (1-31)
     * 다섯 번째 필드: 월 (1-12)
     * 여섯 번째 필드: 요일 (0-6, 일요일부터 토요일까지, 일요일=0 또는 7)
     * 월요일 06:00
     */

    // 데이터 총 개수를 가져와서 numberOfData 넣어줍니다.
//    @Scheduled(cron = "0 0 6 * * 1")
    @Scheduled(cron = "0 52 20 * * *")
    private void updateHotel() {
        rawHotelRepository.deleteAll();
        dataNumber();
        updateData();
    }

    public void dataNumber() {
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.gg.go.kr/StayingGeneralHotel")
                .queryParam("KEY", apiKey)
                .queryParam("Type", "json")
                .queryParam("pSize", 1)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();

        RequestEntity<Void> requestEntity = RequestEntity.get(uri).build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        numberOfData = new JSONObject(responseEntity.getBody())
                .getJSONArray("StayingGeneralHotel").getJSONObject(0)
                .getJSONArray("head").getJSONObject(0)
                .getLong("list_total_count");

        log.info("dataCount = " + numberOfData);
    }

    private void updateData() {
        long page = (numberOfData / BATCH_SIZE) + 1;

        log.info("StayingGeneralHotel" + " Update Scheduling Start");

        for (int i = 1; i <= page; i++) {
            URI uri = UriComponentsBuilder
                    .fromUriString("https://openapi.gg.go.kr/StayingGeneralHotel")
                    .queryParam("KEY", apiKey)
                    .queryParam("Type", "json")
                    .queryParam("pIndex", i)
                    .queryParam("pSize", BATCH_SIZE)
                    .encode(StandardCharsets.UTF_8)
                    .build()
                    .toUri();

            RequestEntity<Void> requestEntity = RequestEntity.get(uri).build();
            ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

            JSONArray rawHotels = new JSONObject(responseEntity.getBody())
                    .getJSONArray("StayingGeneralHotel").getJSONObject(1)
                    .getJSONArray("row");

            saveRawHotelsFromJson(rawHotels);
        }
        log.info("StayingGeneralHotel" + " Update Scheduling End");
    }

    private void saveRawHotelsFromJson(JSONArray rawHotels) {
        // DB에 존재하지 않을 경우 list에 담아두고 한꺼번에 저장하기 위한 용도입니다.
        List<RawHotel> saveRawDataList = new ArrayList<>();

        for (int j = 0; j < rawHotels.length(); j++) {
            JSONObject rawHotel = rawHotels.getJSONObject(j);

            RawHotel newRawHotel = from(rawHotel);
            saveRawDataList.add(newRawHotel);
        }
        rawHotelJdbcRepository.saveAll(saveRawDataList);
    }

    public RawHotel from(JSONObject rawHotel) {
        return RawHotel.builder()
                .sigunNm(rawHotel.optString("SIGUN_NM"))
                .sigunCd(rawHotel.optString("SIGUN_CD"))
                .bizplcNm(rawHotel.optString("BIZPLC_NM"))
                .licensgDe(rawHotel.optString("LICENSG_DE"))
                .bsnStateNm(rawHotel.optString("BSN_STATE_NM"))
                .clsbizDe(rawHotel.optString("CLSBIZ_DE"))
                .locplcAr(Double.parseDouble(rawHotel.optString("LOCPLC_AR", "0")))
                .yy(Integer.parseInt(rawHotel.optString("YY", "0")))
                .multiUseBizestblYn(rawHotel.optString("MULTI_USE_BIZESTBL_YN"))
                .sanittnIndutypeNm(rawHotel.optString("SANITTN_INDUTYPE_NM"))
                .sanittnBizcondNm(rawHotel.optString("SANITTN_BIZCOND_NM"))
                .refineRoadnmAddr(rawHotel.optString("REFINE_ROADNM_ADDR"))
                .refineLotnoAddr(rawHotel.optString("REFINE_LOTNO_ADDR"))
                .refineZipCd(rawHotel.optString("REFINE_ZIP_CD"))
                .refineWgs84Lat(Double.parseDouble(rawHotel.optString("REFINE_WGS84_LAT", "0")))
                .refineWgs84Logt(Double.parseDouble(rawHotel.optString("REFINE_WGS84_LOGT", "0")))
                .build();
    }
}
