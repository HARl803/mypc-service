package com.haribo.mypc_service.application.dto.parts;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemoryDto {
    @Id
    private String id;
    private Memory memory;
    private String partsName;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Memory {
        private String manufacturer;
        private String deviceType;
        private String productTypes;
        private String memoryCapacity;
        private String operatingClock;
        private String ramTiming;
        private double operatingVoltage;
        private int numberOfModules;
        private String features;
        private String heatSink;
        private String heatSinkColor;
        private String ledColor;
        private String ledSystem;
    }
}