package com.haribo.mypc_service.custompc.presentation.request;

import com.haribo.mypc_service.custompc.application.dto.MyComputerDto.MyComputer.Parts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Builder
@AllArgsConstructor
public class MyComputerRequest {
    private String computerName;
    private String id;
    private Boolean isDeleted;

    private Parts cpu;
    private Parts coolerTuning;
    private Parts motherboard;
    private Parts memory;
    private Parts gpu;
    private Parts ssd;
    private Parts hdd;
    @Field("case")
    private Parts _case;
    private Parts power;
}