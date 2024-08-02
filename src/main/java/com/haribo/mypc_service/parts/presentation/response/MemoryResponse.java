package com.haribo.mypc_service.parts.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "parts")
@Getter
public class MemoryResponse {
    private Map<String, Memory> memory;

    @Getter
    public static class Memory{
        @Field("제조사")
        @JsonProperty("제조사")
        String manufacturer;

        @Field("사용 장치")
        @JsonProperty("사용 장치")
        String deviceType;

        @Field("제품 분류")
        @JsonProperty("제품 분류")
        String productTypes;

        @Field("메모리 용량")
        @JsonProperty("메모리 용량")
        String memoryCapacity;

        @Field("동작클럭(대역폭)")
        @JsonProperty("동작클럭(대역폭)")
        String operatingClock;

        @Field("램타이밍")
        @JsonProperty("램타이밍")
        String ramTiming;

        @Field("동작전압")
        @JsonProperty("동작전압")
        String operatingVoltage;

        @Field("램개수")
        @JsonProperty("램개수")
        String numberOfModules;

        @Field("부가기능")
        @JsonProperty("부가기능")
        String features;

        @Field("히트싱크")
        @JsonProperty("히트싱크")
        String heatSink;

        @Field("방열판 색상")
        @JsonProperty("방열판 색상")
        String heatSinkColor;

        @Field("LED색상")
        @JsonProperty("LED색상")
        String ledColor;

        @Field("LED 시스템")
        @JsonProperty("LED 시스템")
        String ledSystem;
    }
}