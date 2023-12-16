package com.example.locationbasedfoodieservice.domain.hotel.repository;

import com.example.locationbasedfoodieservice.domain.hotel.Hotel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query(value =
            "SELECT * ( 6371 * acos( cos(radians(:inputLatitude)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:inputLongitude)) + "
                    + "sin(radians(:inputLatitude)) * sin(radians(latitude)))) AS distance " +
            "FROM restaurant " +
            "HAVING distance <= 1.0", nativeQuery = true)
    List<Hotel> findByLocation(@Param("inputLatitude") Double inputLatitude, @Param("inputLongitude") Double inputLongitude);
}
