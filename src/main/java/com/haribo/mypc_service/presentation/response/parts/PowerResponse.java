package com.haribo.mypc_service.presentation.response.parts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "parts")
@Getter
public class PowerResponse {
    private Map<String, Power> power;

    @Getter
    public static class Power{
        @Field("제조사")
        @JsonProperty("제조사")
        String manufacturer;

        @Field("제품 분류")
        @JsonProperty("제품 분류")
        String productType;

        @Field("정격출력")
        @JsonProperty("정격출력")
        String ratedOutput;

        @Field("80PLUS인증")
        @JsonProperty("80PLUS인증")
        String certification80Plus;

        @Field("ETA인증")
        @JsonProperty("ETA인증")
        String certificationETA;

        @Field("LAMBDA인증")
        @JsonProperty("LAMBDA인증")
        String certificationLAMBDA;

        @Field("+12V 출력방식")
        @JsonProperty("+12V 출력방식")
        String outputType12V;

        @Field("PFC회로")
        @JsonProperty("PFC회로")
        String pfcCircuit;

        @Field("쿨링팬 크기")
        @JsonProperty("쿨링팬 크기")
        String coolingFanSize;

        @Field("A/S 보증기간")
        @JsonProperty("A/S 보증기간")
        String warrantyPeriod;

        @Field("케이블연결")
        @JsonProperty("케이블연결")
        String cableConnection;

        @Field("메인전원")
        @JsonProperty("메인전원")
        String mainPower;

        @Field("보조전원")
        @JsonProperty("보조전원")
        String auxiliaryPower;

        @Field("PCIe 16핀(12+4)")
        @JsonProperty("PCIe 16핀(12+4)")
        String pcie16pin;

        @Field("부가기능")
        @JsonProperty("부가기능")
        String additionalFeatures;

        @Field("LED 시스템")
        @JsonProperty("LED 시스템")
        String ledSystem;
    }
}