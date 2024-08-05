package com.haribo.mypc_service.parts.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "parts")
@Getter
public class SsdResponse extends PartsResponse{
    private Map<String, Ssd> ssd;

    public SsdResponse(Map<String, Ssd> ssd) {
        this.ssd = ssd;
    }

    @Getter
    public static class Ssd{
        @Field("제조사")
        @JsonProperty("제조사")
        String manufacturer;

        @Field("폼팩터")
        @JsonProperty("폼팩터")
        String formFactor;

        @Field("인터페이스")
        @JsonProperty("인터페이스")
        String interfaceType;

        @Field("프로토콜")
        @JsonProperty("프로토콜")
        String protocol;

        @Field("용량")
        @JsonProperty("용량")
        String capacity;

        @Field("메모리 타입")
        @JsonProperty("메모리 타입")
        String memoryType;

        @Field("낸드 구조")
        @JsonProperty("낸드 구조")
        String nandStructure;

        @Field("공정")
        @JsonProperty("공정")
        String process;

        @Field("RAM 탑재")
        @JsonProperty("RAM 탑재")
        String ramEmbedded;

        @Field("RAM 타입")
        @JsonProperty("RAM 타입")
        String ramType;

        @Field("컨트롤러")
        @JsonProperty("컨트롤러")
        String controller;

        @Field("순차읽기")
        @JsonProperty("순차읽기")
        String sequentialRead;

        @Field("순차쓰기")
        @JsonProperty("순차쓰기")
        String sequentialWrite;

        @Field("읽기IOPS")
        @JsonProperty("읽기IOPS")
        String readIOPS;

        @Field("쓰기IOPS")
        @JsonProperty("쓰기IOPS")
        String writeIOPS;

        @Field("지원기능")
        @JsonProperty("지원기능")
        String supportFeatures;

        @Field("부가기능")
        @JsonProperty("부가기능")
        String additionalFeatures;

        @Field("MTBF")
        @JsonProperty("MTBF")
        String mtbf;

        @Field("TBW")
        @JsonProperty("TBW")
        String tbw;

        @Field("A/S기간")
        @JsonProperty("A/S기간")
        String warranty;
    }
}