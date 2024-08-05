package com.haribo.mypc_service.parts.application.service;

import com.haribo.mypc_service.parts.presentation.response.PartsResponse;

import java.util.List;

public interface PartsService {
    <T extends PartsResponse> List<T> getAllParts(String partsName) ;
}