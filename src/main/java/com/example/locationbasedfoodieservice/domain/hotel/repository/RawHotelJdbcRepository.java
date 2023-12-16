package com.example.locationbasedfoodieservice.domain.hotel.repository;

import com.example.locationbasedfoodieservice.domain.hotel.RawHotel;
import java.sql.PreparedStatement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class RawHotelJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveAll(List<RawHotel> rawHotels) {
        String sql =
                "INSERT INTO raw_hotel (sigun_nm,sigun_cd,bizplc_nm,licensg_de,bsn_state_nm,clsbiz_de,locplc_ar,"
                        + "yy,multi_use_bizestbl_yn,sanittn_indutype_nm,sanittn_bizcond_nm,"
                        + "refine_roadnm_addr,refine_lotno_addr,"
                        + "refine_zip_cd,refine_wgs84lat,refine_wgs84logt) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.batchUpdate(sql,
                rawHotels,
                rawHotels.size(),
                (PreparedStatement ps, RawHotel rawRestaurant) -> {
                    ps.setString(1, rawRestaurant.getSigunNm());
                    ps.setString(2, rawRestaurant.getSigunCd());
                    ps.setString(3, rawRestaurant.getBizplcNm());
                    ps.setString(4, rawRestaurant.getLicensgDe());
                    ps.setString(5, rawRestaurant.getBsnStateNm());
                    ps.setString(6, rawRestaurant.getClsbizDe());
                    ps.setDouble(7, rawRestaurant.getLocplcAr());
                    ps.setInt(8, rawRestaurant.getYy());
                    ps.setString(9, rawRestaurant.getMultiUseBizestblYn());
                    ps.setString(10, rawRestaurant.getSanittnIndutypeNm());
                    ps.setString(11, rawRestaurant.getSanittnBizcondNm());
                    ps.setString(12, rawRestaurant.getRefineRoadnmAddr());
                    ps.setString(13, rawRestaurant.getRefineLotnoAddr());
                    ps.setString(14, rawRestaurant.getRefineZipCd());
                    ps.setDouble(15, rawRestaurant.getRefineWgs84Lat());
                    ps.setDouble(16, rawRestaurant.getRefineWgs84Logt());
                }
        );
    }

}
