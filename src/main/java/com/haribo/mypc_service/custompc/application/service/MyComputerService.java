package com.haribo.mypc_service.custompc.application.service;

import com.haribo.mypc_service.custompc.presentation.request.MyComputerRequest;
import com.haribo.mypc_service.custompc.presentation.response.MyComputerResponse;

import java.util.List;

public interface MyComputerService {

    int countMyComputer(String profileId);
    List<MyComputerResponse> getMyComputerList(String profileId);
    MyComputerResponse getMyComputerDto(String profileId, String pcName);
    void addMyComputer(MyComputerRequest myComputer, String profileId);
    void updateMyComputerDto(String profileId, MyComputerRequest myComputer);
    void deleteMyComputerDto(String profileId, String computerId);
}