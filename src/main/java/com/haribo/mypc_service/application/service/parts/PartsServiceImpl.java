package com.haribo.mypc_service.application.service.parts;

import com.haribo.mypc_service.application.dto.parts.*;
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
        query.addCriteria(Criteria.where("partsName").is(partsName));

        switch(partsName) {
            case "cpu": return mongoTemplate.find(query, (Class<T>) CpuDto.class);
            case "memory": return mongoTemplate.find(query, (Class<T>) MemoryDto.class);
            case "case": return mongoTemplate.find(query, (Class<T>) CaseDto.class);
            case "cooler": return mongoTemplate.find(query, (Class<T>) CoolerDto.class);
            case "hdd": return mongoTemplate.find(query, (Class<T>) HddDto.class);
            case "sdd": return mongoTemplate.find(query, (Class<T>) SddDto.class);
            case "powersupply": return mongoTemplate.find(query, (Class<T>) PowerSupplyDto.class);
            case "motherboard": return mongoTemplate.find(query, (Class<T>) MotherBoardDto.class);
            default: return mongoTemplate.find(query, (Class<T>) GpuDto.class);
        }
    }
}