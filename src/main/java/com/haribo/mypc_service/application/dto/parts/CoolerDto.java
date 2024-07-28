package com.haribo.mypc_service.application.dto.parts;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parts")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoolerDto {
    @Id
    private String id;
    private Cooler cooler;
    private String partsName;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Cooler {
        private String manufacturer;
        private String productType;
        private String coolingType;
        private String warranty;
        private String intelSocket;
        private String amdSocket;
        private double height;
        private String radiator;
        private String fanSize;
        private String bearingType;
        private int maxFanSpeed;
        private int maxAirFlow;
        private int minFanNoise;
        private int maxFanNoise;
        private String ledSystem;
        private String additionalFeatures;
        private String thermalType;
    }
}