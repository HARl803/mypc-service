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
public class CpuDto{
    @Id
    private String id;
    private Cpu cpu;
    private String partsName;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Cpu{
        private String manufacturer;
        private String intelCpuTypes;
        private String amdCpuTypes;
        private String socketTypes;
        private String manufacturingProcesses;
        private int coreCounts;
        private int threadCounts;
        private double baseClocks;
        private double boostClocks;
        private int l3Caches;
        private int tdps;
        private String pcieVersions;
        private int maxPcieLanes;
        private String maxMemorySizes;
        private String memoryTypes;
        private String memorySpeeds;
        private String integratedGraphics;
        private String gpuModels;
        private String supportedTechnologies;
        private String codeNames;
        private String packageTypes;
        private String coolerTypes;
    }
}