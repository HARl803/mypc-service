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
public class SddDto {
    @Id
    private String id;
    private Sdd sdd;
    private String partsName;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sdd {
        private String manufacturer;
        private String formFactor;
        private String _interface;
        private String protocol;
        private String capacity;
        private String memoryType;
        private String nandStructure;
        private String process;
        private boolean ramEmbedded;
        private String ramType;
        private String controller;
        private double sequentialRead;
        private double sequentialWrite;
        private int readIOPS;
        private int writeIOPS;
        private String supportFeatures;
        private String additionalFeatures;
        private double mtbf;
        private double tbw;
        private String warranty;
    }
}
