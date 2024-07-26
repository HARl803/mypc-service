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
public class HddDto {
    @Id
    private String id;
    private Hdd hdd;
    private String partsName;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Hdd {
        private String manufacturer;
        private String productType;
        private String diskSize;
        private String diskCapacity;
        private String _interface;
        private int rotationSpeed;
        private String bufferSize;
        private double transferSpeed;
        private String recordingMethod;
        private int diskCount;
        private String additionalFeatures;
        private double mtbf;
        private String warranty;
    }
}
