package com.haribo.mypc_service.parts.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "parts")
@Getter
public class GpuResponse {
    private Map<String, Gpu> gpu;

    @Getter
    public static class Gpu{
        @Field("제조사")
        @JsonProperty("제조사")
        String manufacturer;

        @Field("칩셋 제조사")
        @JsonProperty("칩셋 제조사")
        String chipsetManufacturer;

        @Field("제품 시리즈")
        @JsonProperty("제품 시리즈")
        String productSeries;

        @Field("GPU 제조 공정")
        @JsonProperty("GPU 제조 공정")
        String gpuManufacturingProcess;

        @Field("NVIDIA 칩셋")
        @JsonProperty("NVIDIA 칩셋")
        String nvidiaChipset;

        @Field("AMD 칩셋")
        @JsonProperty("AMD 칩셋")
        String amdChipset;

        @Field("인텔 칩셋")
        @JsonProperty("인텔 칩셋")
        String intelChipset;

        @Field("베이스클럭")
        @JsonProperty("베이스클럭")
        String baseClock;

        @Field("부스트클럭")
        @JsonProperty("부스트클럭")
        String boostClock;

        @Field("스트림 프로세서")
        @JsonProperty("스트림 프로세서")
        String streamProcessors;

        @Field("인터페이스")
        @JsonProperty("인터페이스")
        String interfaceType;

        @Field("메모리 종류")
        @JsonProperty("메모리 종류")
        String memoryType;

        @Field("메모리 용량")
        @JsonProperty("메모리 용량")
        String memoryCapacity;

        @Field("메모리 버스")
        @JsonProperty("메모리 버스")
        String memoryBus;

        @Field("출력단자")
        @JsonProperty("출력단자")
        String outputPort;

        @Field("DVI")
        @JsonProperty("DVI")
        String dvi;

        @Field("HDMI")
        @JsonProperty("HDMI")
        String hdmi;

        @Field("DisplayPort")
        @JsonProperty("DisplayPort")
        String displayPort;

        @Field("모니터 지원")
        @JsonProperty("모니터 지원")
        String monitorSupport;

        @Field("지원기능")
        @JsonProperty("지원기능")
        String supportFeatures;

        @Field("사용전력")
        @JsonProperty("사용전력")
        String powerConsumption;

        @Field("권장 파워용량")
        @JsonProperty("권장 파워용량")
        String recommendedPower;

        @Field("전원 포트")
        @JsonProperty("전원 포트")
        String powerPorts;

        @Field("가로(길이)")
        @JsonProperty("가로(길이)")
        String widthLength;

        @Field("두께")
        @JsonProperty("두께")
        String thickness;

        @Field("부가기능")
        @JsonProperty("부가기능")
        String additionalFeatures;

        @Field("LED 시스템")
        @JsonProperty("LED 시스템")
        String ledSystem;

        @Field("제품보증기간")
        @JsonProperty("제품보증기간")
        String warrantyPeriod;
    }
}