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
public class PowerSupplyDto {
    @Id
    private String id;
    private PowerSupply powerSupply;
    private String partsName;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PowerSupply {
        private String manufacturer;
        private String productType;
        private double ratedOutput;
        private String certification80Plus;
        private String certificationETA;
        private String certificationLAMBDA;
        private String outputType12V;
        private String pfcCircuit;
        private String coolingFanSize;
        private String warrantyPeriod;
        private String cableConnection;
        private String mainPower;
        private String auxiliaryPower;
        private String pcie16pin;
        private String additionalFeatures;
        private String ledSystem;
    }
}
