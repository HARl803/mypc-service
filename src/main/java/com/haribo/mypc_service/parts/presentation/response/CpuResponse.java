package com.haribo.mypc_service.parts.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "parts")
@Getter
public class CpuResponse extends PartsResponse{
    private Map<String, Cpu> cpu;

    public CpuResponse(Map<String, Cpu> cpu) {
        this.cpu = cpu;
    }

    @Getter
    public static class Cpu{
        @Field("제조사")
        @JsonProperty("제조사")
        String manufacturer;
        @Field("인텔 CPU종류")
        @JsonProperty("인텔 CPU종류")
        String intelCpuTypes;
        @Field("AMD CPU종류")
        @JsonProperty("AMD CPU종류")
        String amdCpuTypes;
        @Field("소켓 구분")
        @JsonProperty("소켓 구분")
        String socketTypes;
        @Field("제조 공정")
        @JsonProperty("제조 공정")
        String manufacturingProcesses;
        @Field("코어 수")
        @JsonProperty("코어 수")
        String coreCounts;
        @Field("스레드 수")
        @JsonProperty("스레드 수")
        String threadCounts;
        @Field("기본 클럭")
        @JsonProperty("기본 클럭")
        String baseClocks;
        @Field("최대 클럭")
        @JsonProperty("최대 클럭")
        String boostClocks;
        @Field("L3 캐시")
        @JsonProperty("L3 캐시")
        String l3Caches;
        @Field("TDP")
        @JsonProperty("TDP")
        String tdps;
        @Field("PCIe 버전")
        @JsonProperty("PCIe 버전")
        String pcieVersions;
        @Field("최대 PCIe 레인수")
        @JsonProperty("최대 PCIe 레인수")
        String maxPcieLanes;
        @Field("최대 메모리 크기")
        @JsonProperty("최대 메모리 크기")
        String maxMemorySizes;
        @Field("메모리 규격")
        @JsonProperty("메모리 규격")
        String memoryTypes;
        @Field("메모리 클럭")
        @JsonProperty("메모리 클럭")
        String memorySpeeds;
        @Field("내장그래픽")
        @JsonProperty("내장그래픽")
        String integratedGraphics;
        @Field("GPU 모델명")
        @JsonProperty("제조GPU 모델명회사")
        String gpuModels;
        @Field("기술 지원")
        @JsonProperty("기술 지원")
        String supportedTechnologies;
        @Field("코드 네임")
        @JsonProperty("코드 네임")
        String codeNames;
        @Field("패키지 형태")
        @JsonProperty("패키지 형태")
        String packageTypes;
        @Field("쿨러")
        @JsonProperty("쿨러")
        String coolerTypes;
    }
}