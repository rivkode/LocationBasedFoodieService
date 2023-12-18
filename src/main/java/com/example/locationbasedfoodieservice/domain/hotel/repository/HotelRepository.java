package com.example.locationbasedfoodieservice.domain.hotel.repository;

import com.example.locationbasedfoodieservice.domain.hotel.Hotel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findByNameAddress(String uniqueKey);

    @Query(value = "SELECT * FROM hotel WHERE longitude BETWEEN :leftLongitude AND :rightLongitude AND latitude BETWEEN :lowerLatitude AND :upperLatitude", nativeQuery = true)
    List<Hotel> findHotelsInSquare(@Param("leftLongitude") Double leftLongitude, @Param("rightLongitude") Double rightLongitude,
            @Param("lowerLatitude") Double lowerLatitude, @Param("upperLatitude") Double upperLatitude);
}
