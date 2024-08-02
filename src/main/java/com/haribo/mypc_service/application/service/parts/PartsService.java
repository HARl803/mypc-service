package com.haribo.mypc_service.application.service.parts;



import java.util.List;

public interface PartsService {
    <T> List<T> getAllParts(String partsName) ;
}