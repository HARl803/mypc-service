package com.haribo.mypc_service.application.service.mycomputer;

import com.haribo.mypc_service.application.dto.MyComputerDto;

import java.util.List;

public interface MyComputerService {

    MyComputerDto addMyComputer(MyComputerDto myComputerDto);
    int countMyComputer(String userId);
    List<MyComputerDto> getMyComputerList(String userId);
    void deleteMyComputerDto(String id);
    MyComputerDto getMyComputerDto(String id);
    MyComputerDto updateMyComputerDto(String id, MyComputerDto myNewComputerDto);
}