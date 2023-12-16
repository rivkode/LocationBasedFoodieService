package com.example.locationbasedfoodieservice.domain.hotel.dto;

import com.example.locationbasedfoodieservice.domain.hotel.Hotel;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class HotelResponseDto {
    private String nameAddress;
    private String city;
    private String name;
    private String licenseDate;
    private String businessStatus;
    private String type;
    private String streetAddress;
    private String lotNumberAddress;
    private String postalCode;
    private Double longitude;
    private Double latitude;

    public static HotelResponseDto from(Hotel hotel) {
        return HotelResponseDto.builder()
                .nameAddress(hotel.getNameAddress())
                .city(hotel.getCity())
                .name(hotel.getName())
                .licenseDate(hotel.getLicenseDate())
                .businessStatus(hotel.getBusinessStatus())
                .type(hotel.getType())
                .streetAddress(hotel.getStreetAddress())
                .lotNumberAddress(hotel.getLotNumberAddress())
                .postalCode(hotel.getPostalCode())
                .longitude(hotel.getLongitude())
                .latitude(hotel.getLatitude())
                .build();
    }

    public static List<HotelResponseDto> from(List<Hotel> hotels) {
        return hotels.stream().map(
                hotel -> HotelResponseDto.builder()
                        .nameAddress(hotel.getNameAddress())
                        .city(hotel.getCity())
                        .name(hotel.getName())
                        .licenseDate(hotel.getLicenseDate())
                        .businessStatus(hotel.getBusinessStatus())
                        .type(hotel.getType())
                        .streetAddress(hotel.getStreetAddress())
                        .lotNumberAddress(hotel.getLotNumberAddress())
                        .postalCode(hotel.getPostalCode())
                        .longitude(hotel.getLongitude())
                        .latitude(hotel.getLatitude())
                        .build()
        ).collect(Collectors.toList());
    }
}
