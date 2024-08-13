package com.haribo.mypc_service.custompc.application.dto;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Builder
public class MyComputer {
    @Id
    private String id;
    private String computerName;
    private Boolean isDeleted;

    private String cpu;
    private String coolerTuning;
    private String motherboard;
    private String memory;
    private String gpu;
    private String ssd;
    private String hdd;
    @Field("case")
    private String pcCase;
    private String power;
}