package com.haribo.mypc_service.custompc.application.dto;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "mycomputer")
@Getter
@Builder
public class MyComputerDto {
    @Id
    private String id;

    @Field("profile_id")
    private String profileId;
    private List<MyComputer> myComputers;
}
