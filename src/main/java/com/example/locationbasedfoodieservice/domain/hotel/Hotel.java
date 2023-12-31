package com.example.locationbasedfoodieservice.domain.hotel;

import com.example.locationbasedfoodieservice.domain.review.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hotel {

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
    private Double rating;

    @Column
    private Double longitude;

    @Column
    private Double latitude;

    @OneToMany(mappedBy = "hotel")
    private List<Review> reviews = new ArrayList<>();


    @Builder
    public Hotel(Long id, String nameAddress, String city, String name, String licenseDate,
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
        this.rating = 0.0;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public void updateRating(double rating) {
        this.rating = rating;
    }

    public void update(RawHotel rawHotel) {
        this.city = rawHotel.getSigunNm();
        this.name = rawHotel.getBizplcNm();
        this.licenseDate = rawHotel.getLicensgDe();
        this.businessStatus = rawHotel.getBsnStateNm();
        this.type = rawHotel.getSanittnBizcondNm();
        this.streetAddress = rawHotel.getRefineRoadnmAddr();
        this.lotNumberAddress = rawHotel.getRefineLotnoAddr();
        this.postalCode = rawHotel.getRefineZipCd();
        this.longitude = rawHotel.getRefineWgs84Logt();
        this.latitude = rawHotel.getRefineWgs84Lat();
    }
}
