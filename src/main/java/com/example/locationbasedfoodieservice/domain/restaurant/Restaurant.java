package com.example.locationbasedfoodieservice.domain.restaurant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nameAddress;

    @Column
    private String city;

    @Column
    private String name;

    @Column
    private String licenseDate;

    @Column
    private String businessStatus;

    @Column
    private String type;

    @Column
    private String streetAddress;

    @Column
    private String lotNumberAddress;

    @Column
    private String postalCode;

    @Column
    private Double longitude;

    @Column
    private Double latitude;


    @Builder
    public Restaurant(Long id, String nameAddress, String city, String name, String licenseDate,
            String businessStatus,
            String type, String streetAddress, String lotNumberAddress, String postalCode,
            Double longitude, Double latitude) {
        this.id = id;
        this.nameAddress = nameAddress;
        this.city = city;
        this.name = name;
        this.licenseDate = licenseDate;
        this.businessStatus = businessStatus;
        this.type = type;
        this.streetAddress = streetAddress;
        this.lotNumberAddress = lotNumberAddress;
        this.postalCode = postalCode;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
