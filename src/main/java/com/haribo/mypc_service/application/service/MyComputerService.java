package com.haribo.mypc_service.application.service;

import com.haribo.mypc_service.application.dto.MyComputerDto;

import java.util.List;

public interface MyComputerService {

    MyComputerDto addMyComputer(MyComputerDto myComputerDto);
    int countMyComputer(String userId);
    List<MyComputerDto> getMyComputerList(String userId);
    void deleteMyComputerDto(String userId, String computerName);

}