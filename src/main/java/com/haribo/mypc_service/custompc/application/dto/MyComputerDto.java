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

        @Getter
        public static class Parts {
            private String partsName;
            private String id;
        }
    }
}
