package com.haribo.mypc_service.parts.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "parts")
@Getter
public class CoolerResponse extends PartsResponse{
    private Map<String, Cooler> coolerTuning;

    public CoolerResponse(Map<String, Cooler> coolerTuning) {
        this.coolerTuning = coolerTuning;
    }

    @Getter
    public static class Cooler{
        @Field("제조사")
        @JsonProperty("제조사")
        String manufacturer;

        @Field("제품 종류")
        @JsonProperty("제품 종류")
        String productType;

        @Field("냉각 방식")
        @JsonProperty("냉각 방식")
        String coolingType;

        @Field("A/S기간")
        @JsonProperty("A/S기간")
        String warranty;

        @Field("인텔 소켓")
        @JsonProperty("인텔 소켓")
        String intelSocket;

        @Field("AMD 소켓")
        @JsonProperty("AMD 소켓")
        String amdSocket;

        @Field("높이")
        @JsonProperty("높이")
        String height;

        @Field("라디에이터")
        @JsonProperty("라디에이터")
        String radiator;

        @Field("팬 크기")
        @JsonProperty("팬 크기")
        String fanSize;

        @Field("베어링")
        @JsonProperty("베어링")
        String bearingType;

        @Field("최대 팬속도")
        @JsonProperty("최대 팬속도")
        String maxFanSpeed;

        @Field("최대 풍량")
        @JsonProperty("최대 풍량")
        String maxAirFlow;

        @Field("최저 팬소음")
        @JsonProperty("최저 팬소음")
        String minFanNoise;

        @Field("최대 팬소음")
        @JsonProperty("최대 팬소음")
        String maxFanNoise;

        @Field("LED시스템")
        @JsonProperty("LED시스템")
        String ledSystem;

        @Field("부가기능")
        @JsonProperty("부가기능")
        String additionalFeatures;

        @Field("써멀유형")
        @JsonProperty("써멀유형")
        String thermalType;
    }
}