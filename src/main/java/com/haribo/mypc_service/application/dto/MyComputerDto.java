package com.haribo.mypc_service.application.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mycomputer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyComputerDto {

    @Id
    private String id;
    private String userId;
    private String computerName;
    private Boolean isDelete;
    private MyComputer myComputer;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class MyComputer {
        private String cpuId;
        private String coolerId;
        private String motherboardId;
        private String graphicsCardId;
        private String ssdId;
        private String hddId;
        private String pcCaseId;
        private String poserSupplyId;
        private String memoryId;
    }
}