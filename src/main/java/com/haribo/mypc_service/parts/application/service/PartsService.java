package com.haribo.mypc_service.parts.application.service;

import java.util.List;

public interface PartsService {
    <T> List<T> getAllParts(String partsName) ;
}