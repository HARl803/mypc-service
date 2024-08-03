package com.haribo.mypc_service.custompc.application.service;

import com.haribo.mypc_service.common.exception.CustomErrorCode;
import com.haribo.mypc_service.common.exception.CustomException;
import com.haribo.mypc_service.custompc.application.dto.MyComputerDto;
import com.haribo.mypc_service.custompc.application.dto.MyComputerDto.MyComputer;
import com.haribo.mypc_service.custompc.presentation.request.MyComputerRequest;
import com.haribo.mypc_service.custompc.presentation.response.MyComputerResponse;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class MyComputerServiceImpl implements MyComputerService {

    private final MongoTemplate mongoTemplate;
    private final Logger logger = LoggerFactory.getLogger(MyComputerServiceImpl.class);

    @Override
    public int countMyComputer(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));

        List<MyComputerDto> documents = mongoTemplate.find(query, MyComputerDto.class, "mycomputer");

        int myComputerCnt = 0;

        for (MyComputerDto dto : documents) {
            List<MyComputer> myComputers = dto.getMyComputers();

            for (MyComputer myComputer : myComputers) {
                if (!myComputer.getIsDeleted()) myComputerCnt++;
            }
        }

        return myComputerCnt;
    }

    @Override
    public List<MyComputerResponse> getMyComputerList(String userId) {

        List<MyComputerResponse> myComputerResponses = new ArrayList<>();

        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));

        List<MyComputerDto> documents = mongoTemplate.find(query, MyComputerDto.class, "mycomputer");

        if(documents.isEmpty()) throw new CustomException(CustomErrorCode.USER_NOT_FOUND);

        for (MyComputerDto dto : documents) {
            List<MyComputer> myComputers = dto.getMyComputers();

            for (MyComputer myComputer : myComputers) {
                if (!myComputer.getIsDeleted()) {
                    MyComputerResponse myComputerResponse = MyComputerResponse.builder()
                            .id(myComputer.getId())
                            .computerName(myComputer.getComputerName())
                            .cpu(myComputer.getCpu())
                            .memory(myComputer.getMemory())
                            .ssd(myComputer.getSsd())
                            .hdd(myComputer.getHdd())
                            .motherboard(myComputer.getMotherboard())
                            .power(myComputer.getPower())
                            .gpu(myComputer.getGpu())
                            .coolerTuning(myComputer.getCoolerTuning())
                            .pcCase(myComputer.getPcCase())
                            .isDelete(myComputer.getIsDeleted())
                            .build();

                    myComputerResponses.add(myComputerResponse);
                }
            }
        }
        return myComputerResponses;
    }

    @Override
    public MyComputerResponse getMyComputerDto(String userId, String computerId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));

        List<MyComputerDto> documents = mongoTemplate.find(query, MyComputerDto.class, "mycomputer");

        if(documents.isEmpty()) throw new CustomException(CustomErrorCode.USER_NOT_FOUND);

        for (MyComputerDto dto : documents) {
            for (MyComputer myComputer : dto.getMyComputers()) {
                if (!myComputer.getIsDeleted() && myComputer.getId().equals(computerId)) {
                    return MyComputerResponse.builder()
                            .computerName(myComputer.getComputerName())
                            .cpu(myComputer.getCpu())
                            .memory(myComputer.getMemory())
                            .ssd(myComputer.getSsd())
                            .hdd(myComputer.getHdd())
                            .motherboard(myComputer.getMotherboard())
                            .power(myComputer.getPower())
                            .gpu(myComputer.getGpu())
                            .coolerTuning(myComputer.getCoolerTuning())
                            .pcCase(myComputer.getPcCase())
                            .isDelete(myComputer.getIsDeleted())
                            .build();
                }
            }
        }

        throw new CustomException(CustomErrorCode.CUSTOM_PC_NOT_FOUND);
    }

    @Override
    public void addMyComputer(MyComputerRequest myComputerRequest, String userId){

        logger.debug("개수: {}", countMyComputer(userId));

        if(countMyComputer(userId)>=5)
            throw new CustomException(CustomErrorCode.SIZE_FULL_ERROR);

        MyComputer myComputer = MyComputer.builder()
                .id(UUID.randomUUID().toString())
                .computerName(myComputerRequest.getComputerName())
                .cpu(myComputerRequest.getCpu())
                .memory(myComputerRequest.getMemory())
                .ssd(myComputerRequest.getSsd())
                .hdd(myComputerRequest.getHdd())
                .motherboard(myComputerRequest.getMotherboard())
                .power(myComputerRequest.getPower())
                .gpu(myComputerRequest.getGpu())
                .coolerTuning(myComputerRequest.getCoolerTuning())
                .pcCase(myComputerRequest.getPcCase())
                .isDeleted(false)
                .build();

        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));

        Update update = new Update();
        update.push("myComputers", myComputer);

        if(mongoTemplate.findOne(new Query().addCriteria(Criteria.where("userId").is(userId)), MyComputerDto.class)!=null){
            mongoTemplate.updateFirst(query, update, "mycomputer");
        } else {
            System.out.println("hi");
            logger.debug("새로운 도큐먼트 생성 -> ID: {}", userId);
            List<MyComputer> myComputerList = new ArrayList<>();
            myComputerList.add(myComputer);
            MyComputerDto myComputerDto = MyComputerDto.builder()
                    .userId(userId)
                    .myComputers(myComputerList)
                    .build();
            mongoTemplate.save(myComputerDto, "mycomputer");
        }
    }

    @Override
    public void updateMyComputerDto(String userId, MyComputerRequest myComputerRequest) {

        logger.debug("request 컴퓨터 이름: {}", myComputerRequest.getId());

        String computerId = myComputerRequest.getId();

        // 여기서 내가 원하는 computer를 찾았어
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        if(mongoTemplate.findOne(query, MyComputerDto.class)==null){
            throw new CustomException(CustomErrorCode.USER_NOT_FOUND);
        }

        query.addCriteria(Criteria.where("myComputers._id").is(computerId));

        MyComputer myComputer = MyComputer.builder()
                .id(myComputerRequest.getId())
                .isDeleted(myComputerRequest.getIsDeleted())
                .computerName(myComputerRequest.getComputerName())
                .cpu(myComputerRequest.getCpu())
                .memory(myComputerRequest.getMemory())
                .ssd(myComputerRequest.getSsd())
                .hdd(myComputerRequest.getHdd())
                .motherboard(myComputerRequest.getMotherboard())
                .power(myComputerRequest.getPower())
                .gpu(myComputerRequest.getGpu())
                .coolerTuning(myComputerRequest.getCoolerTuning())
                .pcCase(myComputerRequest.getPcCase())
                .build();

        Update update = new Update();
        update.set("myComputers.$", myComputer);

        mongoTemplate.updateFirst(query, update, MyComputerDto.class);
    }


    @Override
    public void deleteMyComputerDto(String userId, String computerId){
        MyComputerResponse myComputerDto = getMyComputerDto(userId, computerId);

        logger.debug("computerDto의 이름 : {}", myComputerDto.getComputerName());

        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        query.addCriteria(Criteria.where("myComputers._id").is(computerId));

        Update update = new Update();
        update.set("myComputers.$[myComputer].isDeleted", true);

        UpdateOptions options = new UpdateOptions()
                .arrayFilters(Arrays.asList(
                        Filters.eq("myComputer._id", computerId)
                ));

        mongoTemplate.getCollection("mycomputer")
                .updateOne(query.getQueryObject(), update.getUpdateObject(), options);
    }
}
