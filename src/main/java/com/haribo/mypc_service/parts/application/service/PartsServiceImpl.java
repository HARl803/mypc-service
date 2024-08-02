package com.haribo.mypc_service.parts.application.service;

import com.haribo.mypc_service.parts.presentation.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PartsServiceImpl implements PartsService {

    private final MongoTemplate mongoTemplate;

    @Override
    public <T> List<T> getAllParts(String partsName) {
        Query query = new Query();
        query.addCriteria(Criteria.where(partsName).exists(true));

        switch(partsName) {
            case "cpu": return mongoTemplate.find(query, (Class<T>) CpuResponse.class, "parts");
            case "memory": return mongoTemplate.find(query, (Class<T>) MemoryResponse.class, "parts");
            case "case": return mongoTemplate.find(query, (Class<T>) CaseResponse.class, "parts");
            case "coolerTuning": return mongoTemplate.find(query, (Class<T>) CoolerResponse.class, "parts");
            case "hdd": return mongoTemplate.find(query, (Class<T>) HddResponse.class, "parts");
            case "sdd": return mongoTemplate.find(query, (Class<T>) SsdResponse.class, "parts");
            case "power": return mongoTemplate.find(query, (Class<T>) PowerResponse.class, "parts");
            case "motherboard": return mongoTemplate.find(query, (Class<T>) MotherboardResponse.class, "parts");
            default: return mongoTemplate.find(query, (Class<T>) GpuResponse.class, "parts");
        }
    }
}