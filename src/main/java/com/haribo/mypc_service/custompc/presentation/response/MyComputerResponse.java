package com.haribo.mypc_service.custompc.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Builder
@AllArgsConstructor
public class MyComputerResponse {
    private String computerName;
    private String id;

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
