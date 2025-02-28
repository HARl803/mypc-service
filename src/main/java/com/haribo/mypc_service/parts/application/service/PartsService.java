package com.haribo.mypc_service.parts.application.service;

import com.haribo.mypc_service.parts.presentation.response.PartsResponse;
import java.util.Map;

public interface PartsService {
    <T extends PartsResponse> T getAllParts(String partsName, Class<T> partsResponseType);
    Map<?, ?> getParts();
}