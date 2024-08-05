package com.haribo.mypc_service.custompc.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import com.haribo.mypc_service.custompc.application.dto.MyComputerDto.MyComputer.Parts;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Builder
@AllArgsConstructor
public class MyComputerResponse {
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
