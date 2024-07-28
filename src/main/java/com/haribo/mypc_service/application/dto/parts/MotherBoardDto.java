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
public class MotherBoardDto {
    @Id
    private String id;
    private MotherBoard motherBoard;
    private String partsName;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MotherBoard {
        private String manufacturer;
        private String productType;
        private String cpuSocket;
        private String chipset;
        private String formFactor;
        private String powerPhase;
        private String vcoreOutput;
        private String memoryType;
        private String memorySpeed;
        private String memorySlots;
        private String memoryCapacity;
        private String vgaConnection;
        private String pcieVersion;
        private String sata;
        private String m2;
        private String graphicOutput;
        private String rearPorts;
        private String rearUsb3_20Gbps;
        private String rearUsb2_0;
        private String wiredLanChipset;
        private String wirelessLanType;
        private String audioChipset;
        private String ledSystem;
    }
}