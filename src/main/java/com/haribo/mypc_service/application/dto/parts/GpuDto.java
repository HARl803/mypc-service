package com.haribo.mypc_service.application.dto.parts;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GpuDto {
    @Id
    private String id;
    private Gpu gpu;
    private String partsName;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Gpu {
        private String manufacturer;
        private String chipsetManufacturer;
        private String productSeries;
        private String gpuManufacturingProcess;
        private String nvidiaChipset;
        private String amdChipset;
        private String intelChipset;
        private String baseClock;
        private String boostClock;
        private String streamProcessors;
        private String _interface;
        private String memoryType;
        private String memoryCapacity;
        private String memoryBus;
        private String outputPort;
        private String dvi;
        private String hdmi;
        private String displayPort;
        private String monitorSupport;
        private String supportFeatures;
        private String powerConsumption;
        private String recommendedPower;
        private String powerPorts;
        private String widthLength;
        private String thickness;
        private String additionalFeatures;
        private String ledSystem;
        private String warrantyPeriod;
    }
}