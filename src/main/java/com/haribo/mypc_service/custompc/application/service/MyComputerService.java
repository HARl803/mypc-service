package com.haribo.mypc_service.custompc.application.service;

import com.haribo.mypc_service.custompc.presentation.request.MyComputerRequest;
import com.haribo.mypc_service.custompc.presentation.response.MyComputerResponse;

import java.util.List;

public interface MyComputerService {

    int countMyComputer(String userId);
    List<MyComputerResponse> getMyComputerList(String userId);
    MyComputerResponse getMyComputerDto(String userId, String pcName);
    void addMyComputer(MyComputerRequest myComputer, String userId);
    void updateMyComputerDto(String userId, MyComputerRequest myComputer);
    void deleteMyComputerDto(String userId, String computerId);
}