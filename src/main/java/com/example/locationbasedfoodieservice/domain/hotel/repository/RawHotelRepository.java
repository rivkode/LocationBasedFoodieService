package com.example.locationbasedfoodieservice.domain.hotel.repository;

import com.example.locationbasedfoodieservice.domain.hotel.RawHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawHotelRepository extends JpaRepository<RawHotel, Long> {

}
