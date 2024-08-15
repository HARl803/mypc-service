package com.haribo.mypc_service.parts.application.service;

import com.haribo.mypc_service.parts.presentation.response.*;
import lombok.RequiredArgsConstructor;
import org.bson.json.JsonObject;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PartsServiceImpl implements PartsService {

    private final MongoTemplate mongoTemplate;

    @Override
    public Map<?, ?> getParts() {

        JSONObject responses = new JSONObject();

        CpuResponse cpuResponse = getAllParts("cpu", CpuResponse.class);
        MemoryResponse memoryResponse = getAllParts("memory", MemoryResponse.class);
        CaseResponse caseResponse = getAllParts("case", CaseResponse.class);
        CoolerResponse coolerResponse = getAllParts("coolerTuning", CoolerResponse.class);
        HddResponse hddResponse = getAllParts("hdd", HddResponse.class);
        SsdResponse ssdResponse = getAllParts("ssd", SsdResponse.class);
        PowerResponse powerResponse = getAllParts("power", PowerResponse.class);
        MotherboardResponse motherboardResponse =  getAllParts("motherboard", MotherboardResponse.class);
        GpuResponse gpuResponse = getAllParts("gpu", GpuResponse.class);

        JSONObject response = new JSONObject();
        for (Map.Entry<String, CpuResponse.Cpu> entry : cpuResponse.getCpu().entrySet()){
            response.put(entry.getKey(), entry.getValue());
        }
        responses.put("cpu", response);
        response = new JSONObject();
        for (Map.Entry<String, MemoryResponse.Memory> entry : memoryResponse.getMemory().entrySet()){
            response.put(entry.getKey(), entry.getValue());
        }
        responses.put("memory", response);
        response = new JSONObject();
        for (Map.Entry<String, CaseResponse.Case> entry : caseResponse.getPcCase().entrySet()){
            response.put(entry.getKey(), entry.getValue());
        }
        responses.put("case", response);
        response = new JSONObject();
        for (Map.Entry<String, CoolerResponse.Cooler> entry : coolerResponse.getCoolerTuning().entrySet()){
            response.put(entry.getKey(), entry.getValue());
        }
        responses.put("coolerTuning", response);
        response = new JSONObject();
        for (Map.Entry<String, HddResponse.Hdd> entry : hddResponse.getHdd().entrySet()){
            response.put(entry.getKey(), entry.getValue());
        }
        responses.put("hdd", response);
        response = new JSONObject();
        for (Map.Entry<String, SsdResponse.Ssd> entry : ssdResponse.getSsd().entrySet()){
            response.put(entry.getKey(), entry.getValue());
        }
        responses.put("ssd", response);
        response = new JSONObject();
        for (Map.Entry<String, PowerResponse.Power> entry : powerResponse.getPower().entrySet()){
            response.put(entry.getKey(), entry.getValue());
        }
        responses.put("power", response);
        response = new JSONObject();
        for (Map.Entry<String, MotherboardResponse.Motherboard> entry : motherboardResponse.getMotherboard().entrySet()){
            response.put(entry.getKey(), entry.getValue());
        }
        responses.put("motherboard", response);
        response = new JSONObject();
        for (Map.Entry<String, GpuResponse.Gpu> entry : gpuResponse.getGpu().entrySet()){
            response.put(entry.getKey(), entry.getValue());
        }
        responses.put("gpu", response);

        return responses.toMap();
    }

    @Override
    public <T extends PartsResponse> T getAllParts(String partsName, Class<T> partType) {
        Query query = new Query(Criteria.where(partsName).exists(true));

        return mongoTemplate.findOne(query, partType);
    }
}