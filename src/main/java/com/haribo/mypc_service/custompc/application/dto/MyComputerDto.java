package com.haribo.mypc_service.custompc.application.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "mycomputer")
@Getter
@Builder
public class MyComputerDto {

    private String userId;
    private List<MyComputer> myComputers;

    @Getter
    @Builder
    public static class MyComputer {
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
}
