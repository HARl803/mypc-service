package com.haribo.mypc_service.parts.application.service;

import com.haribo.mypc_service.parts.presentation.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartsServiceImpl implements PartsService {

    private final MongoTemplate mongoTemplate;

    @Override
    public <T extends PartsResponse> T getAllParts(String partsName, Class<T> partType) {
        Query query = new Query(Criteria.where(partsName).exists(true));

        return mongoTemplate.findOne(query, partType);
    }
}