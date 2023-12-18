package com.example.locationbasedfoodieservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LocationBasedFoodieServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationBasedFoodieServiceApplication.class, args);
    }

}
