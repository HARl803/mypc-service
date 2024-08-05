package com.haribo.mypc_service.custompc.presentation.request;

import com.haribo.mypc_service.custompc.application.dto.MyComputerDto.MyComputer.Parts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@AllArgsConstructor
public class MyComputerRequest {
    private String computerName;
    private String id;

    private Parts cpu;
    private Parts coolerTuning;
    private Parts motherboard;
    private Parts memory;
    private Parts gpu;
    private Parts ssd;
    private Parts hdd;
    @Field("case")
    private Parts pcCase;
    private Parts power;
}