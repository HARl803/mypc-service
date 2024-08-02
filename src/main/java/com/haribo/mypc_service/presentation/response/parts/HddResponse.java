package com.haribo.mypc_service.presentation.response.parts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "parts")
@Getter
public class HddResponse {
    private Map<String, Hdd> hdd;

    @Getter
    public static class Hdd{
        @Field("제조사")
        @JsonProperty("제조사")
        String manufacturer;

        @Field("제품 분류")
        @JsonProperty("제품 분류")
        String productType;

        @Field("디스크 크기")
        @JsonProperty("디스크 크기")
        String diskSize;

        @Field("디스크 용량")
        @JsonProperty("디스크 용량")
        String diskCapacity;

        @Field("인터페이스")
        @JsonProperty("인터페이스")
        String interfaceType;

        @Field("회전수")
        @JsonProperty("회전수")
        String rotationSpeed;

        @Field("버퍼 용량")
        @JsonProperty("버퍼 용량")
        String bufferSize;

        @Field("전송 속도")
        @JsonProperty("전송 속도")
        String transferSpeed;

        @Field("기록방식")
        @JsonProperty("기록방식")
        String recordingMethod;

        @Field("디스크 수")
        @JsonProperty("디스크 수")
        String diskCount;

        @Field("부가기능")
        @JsonProperty("부가기능")
        String additionalFeatures;

        @Field("사용보증")
        @JsonProperty("사용보증")
        String mtbf;

        @Field("A/S 정보")
        @JsonProperty("A/S 정보")
        String warranty;
    }
}