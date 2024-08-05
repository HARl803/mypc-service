package com.haribo.mypc_service.parts.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "parts")
@Getter
public class CaseResponse extends PartsResponse{
    private Map<String, Case> pcCase;

    public CaseResponse(Map<String, Case> pcCase) {
        this.pcCase = pcCase;
    }

    @Getter
    public static class Case{
        @Field("제조사")
        @JsonProperty("제조사")
        String manufacturer;

        @Field("제품 분류")
        @JsonProperty("제품 분류")
        String productType;

        @Field("케이스 크기")
        @JsonProperty("케이스 크기")
        String caseSize;

        @Field("지원파워규격")
        @JsonProperty("지원파워규격")
        String powerSupply;

        @Field("지원보드규격")
        @JsonProperty("지원보드규격")
        String boardSupport;

        @Field("13.3cm베이")
        @JsonProperty("13.3cm베이")
        String bay13cm;

        @Field("8.9cm베이")
        @JsonProperty("8.9cm베이")
        String bay89cm;

        @Field("저장장치 장착")
        @JsonProperty("저장장치 장착")
        String storageMount;

        @Field("수직 PCI형태")
        @JsonProperty("수직 PCI형태")
        String verticalPCI;

        @Field("쿨링팬")
        @JsonProperty("쿨링팬")
        String coolingFans;

        @Field("LED팬")
        @JsonProperty("LED팬")
        String ledFans;

        @Field("전면 패널 타입")
        @JsonProperty("전면 패널 타입")
        String frontPanel;

        @Field("측면 개폐 방식")
        @JsonProperty("측면 개폐 방식")
        String sideOpening;

        @Field("측면")
        @JsonProperty("측면")
        String side;

        @Field("후면")
        @JsonProperty("후면")
        String rear;

        @Field("전면")
        @JsonProperty("전면")
        String front;

        @Field("상단")
        @JsonProperty("상단")
        String top;

        @Field("먼지필터")
        @JsonProperty("먼지필터")
        String dustFilter;

        @Field("VGA 장착 길이")
        @JsonProperty("VGA 장착 길이")
        String vgaMountLength;

        @Field("CPU쿨러 장착높이")
        @JsonProperty("CPU쿨러 장착높이")
        String cpuCoolerHeight;

        @Field("수랭쿨러 규격")
        @JsonProperty("수랭쿨러 규격")
        String liquidCoolerSupport;

        @Field("라디에이터(상단)")
        @JsonProperty("라디에이터(상단)")
        String radiatorTop;

        @Field("라디에이터(전면)")
        @JsonProperty("라디에이터(전면)")
        String radiatorFront;

        @Field("라디에이터(후면)")
        @JsonProperty("라디에이터(후면)")
        String radiatorRear;

        @Field("부가기능")
        @JsonProperty("부가기능")
        String additionalFeatures;

        @Field("LED 튜닝")
        @JsonProperty("LED 튜닝")
        String ledTuning;

        @Field("컨트롤러 지원")
        @JsonProperty("컨트롤러 지원")
        String controllerSupport;

        @Field("케이스 색상 계열")
        @JsonProperty("케이스 색상 계열")
        String caseColor;
    }
}