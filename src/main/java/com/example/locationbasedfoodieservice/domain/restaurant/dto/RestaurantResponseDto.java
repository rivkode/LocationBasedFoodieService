package com.example.locationbasedfoodieservice.domain.restaurant.dto;

import com.example.locationbasedfoodieservice.domain.restaurant.Restaurant;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class RestaurantResponseDto {
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

    public static RestaurantResponseDto from(Restaurant restaurant) {
        return RestaurantResponseDto.builder()
                .nameAddress(restaurant.getNameAddress())
                .city(restaurant.getCity())
                .name(restaurant.getName())
                .licenseDate(restaurant.getLicenseDate())
                .businessStatus(restaurant.getBusinessStatus())
                .type(restaurant.getType())
                .streetAddress(restaurant.getStreetAddress())
                .lotNumberAddress(restaurant.getLotNumberAddress())
                .postalCode(restaurant.getPostalCode())
                .longitude(restaurant.getLongitude())
                .latitude(restaurant.getLatitude())
                .build();
    }

    public static List<RestaurantResponseDto> from(List<Restaurant> restaurants) {
        return restaurants.stream().map(
                restaurant -> RestaurantResponseDto.builder()
                        .nameAddress(restaurant.getNameAddress())
                        .city(restaurant.getCity())
                        .name(restaurant.getName())
                        .licenseDate(restaurant.getLicenseDate())
                        .businessStatus(restaurant.getBusinessStatus())
                        .type(restaurant.getType())
                        .streetAddress(restaurant.getStreetAddress())
                        .lotNumberAddress(restaurant.getLotNumberAddress())
                        .postalCode(restaurant.getPostalCode())
                        .longitude(restaurant.getLongitude())
                        .latitude(restaurant.getLatitude())
                        .build()
        ).collect(Collectors.toList());
    }
}
