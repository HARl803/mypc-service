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
public class CaseDto {
    @Id
    private String id;
    private Case _case;
    private String partsName;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Case {
        private String manufacturer;
        private String productType;
        private String caseSize;
        private String powerSupply;
        private String boardSupport;
        private String bay13cm;
        private String bay89cm;
        private String storageMount;
        private String verticalPCI;
        private String coolingFans;
        private String ledFans;
        private String frontPanel;
        private String sideOpening;
        private String side;
        private String rear;
        private String front;
        private String top;
        private String dustFilter;
        private String vgaMountLength;
        private double cpuCoolerHeight;
        private String liquidCoolerSupport;
        private String radiatorTop;
        private String radiatorFront;
        private String radiatorRear;
        private String additionalFeatures;
        private String ledTuning;
        private String controllerSupport;
        private String caseColor;
    }
}
