package com.haribo.mypc_service.custompc.application.service;

import com.haribo.mypc_service.common.exception.CustomErrorCode;
import com.haribo.mypc_service.common.exception.CustomException;
import com.haribo.mypc_service.custompc.application.dto.MyComputerDto;
import com.haribo.mypc_service.custompc.application.dto.MyComputer;
import com.haribo.mypc_service.custompc.presentation.request.MyComputerRequest;
import com.haribo.mypc_service.custompc.presentation.response.MyComputerResponse;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class MyComputerServiceImpl implements MyComputerService {

    private final MongoTemplate mongoTemplate;

    @Override
    public int countMyComputer(String profileId) {

        log.info("내 컴퓨터 갯수 세기!");

        Query query = new Query();
        query.addCriteria(Criteria.where("profileId").is(profileId));

        List<MyComputerDto> documents = mongoTemplate.find(query, MyComputerDto.class, "mycomputer");

        int myComputerCnt = 0;

        for (MyComputerDto dto : documents) {
            List<MyComputer> myComputers = dto.getMyComputers();

            for (MyComputer myComputer : myComputers) {
                if (!myComputer.getIsDeleted()) myComputerCnt++;
            }
        }

        log.debug("내 컴퓨터 갯수: {}", myComputerCnt);

        return myComputerCnt;
    }

    @Override
    public List<MyComputerResponse> getMyComputerList(String profileId) {

        log.info("컴퓨터 리스트 모두 호출하기!");

        List<MyComputerResponse> myComputerResponses = new ArrayList<>();

        Query query = new Query();
        query.addCriteria(Criteria.where("profileId").is(profileId));

        List<MyComputerDto> documents = mongoTemplate.find(query, MyComputerDto.class, "mycomputer");

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
                            .build();

                    myComputerResponses.add(myComputerResponse);
                }
            }
        }
        return myComputerResponses;
    }

    @Override
    public MyComputerResponse getMyComputerDto(String profileId, String computerId) {

        log.info("내가 등록한 컴퓨터 찾기!");

        Query query = new Query();
        query.addCriteria(Criteria.where("profileId").is(profileId));

        List<MyComputerDto> documents = mongoTemplate.find(query, MyComputerDto.class, "mycomputer");

        if(documents.isEmpty()) throw new CustomException(CustomErrorCode.USER_NOT_FOUND);

        for (MyComputerDto dto : documents) {
            for (MyComputer myComputer : dto.getMyComputers()) {
                if (!myComputer.getIsDeleted() && myComputer.getId().equals(computerId)) {
                    return MyComputerResponse.builder()
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
                            .build();
                }
            }
        }

        throw new CustomException(CustomErrorCode.CUSTOM_PC_NOT_FOUND);
    }

    @Override
    public void addMyComputer(MyComputerRequest myComputerRequest, String profileId) {

        log.info("컴퓨터 추가하기!");

        log.debug("개수: {}", countMyComputer(profileId));

        if (countMyComputer(profileId) >= 5)
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
        query.addCriteria(Criteria.where("profileId").is(profileId));

        if (mongoTemplate.findOne(query, MyComputerDto.class, "mycomputer") != null) {
            log.debug("mongoDB에 document가 등록되어있네요. 추가만 할게요");
            UpdateResult result = mongoTemplate.updateFirst(query, new Update().push("myComputers", myComputer), MyComputerDto.class);
            log.debug("업데이트 갯수: {}", result.getModifiedCount());
        } else {
            log.debug("새로운 도큐먼트 생성해야 해요 -> profileId: {}", profileId);
            List<MyComputer> myComputerList = new ArrayList<>();
            myComputerList.add(myComputer);
            mongoTemplate.save(MyComputerDto.builder()
                    .profileId(profileId)
                    .myComputers(myComputerList)
                    .build(), "mycomputer");
        }
    }

    @Override
    public void updateMyComputerDto(String profileId, MyComputerRequest myComputerRequest) {

        log.info("컴퓨터 업데이트! ID도 request에 담아서 보내줘야 해요!");

        String computerId = myComputerRequest.getId();

        log.debug("request 컴퓨터 아이디: {}", computerId);

        // 여기서 내가 원하는 computer를 찾았어
        Query query = new Query();
        query.addCriteria(Criteria.where("myComputers._id").is(computerId));

        MyComputerDto myComputerDto = mongoTemplate.findOne(query, MyComputerDto.class, "mycomputer");

        log.info("profileId: {}", mongoTemplate.findOne(query, MyComputerDto.class, "mycomputer").getProfileId());

        if(mongoTemplate.findOne(query, MyComputerDto.class)==null){
            throw new CustomException(CustomErrorCode.CUSTOM_PC_NOT_FOUND);
        }

        if(myComputerDto.getProfileId().equals(profileId)) {

            MyComputer myComputer = MyComputer.builder()
                    .id(myComputerRequest.getId())
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

            Update update = new Update();
            update.set("myComputers.$", myComputer);

            mongoTemplate.updateFirst(query, update, MyComputerDto.class);
        } else throw new CustomException(CustomErrorCode.CAN_NOT_UPDATED);
    }


    @Override
    public void deleteMyComputerDto(String profileId, String computerId) {

        log.info("해당 컴퓨터 삭제하기!");

        Query query = new Query();
        query.addCriteria(Criteria.where("myComputers._id").is(computerId));

        if (mongoTemplate.findOne(query, MyComputerDto.class) == null) {
            throw new CustomException(CustomErrorCode.CUSTOM_PC_NOT_FOUND);
        }

        MyComputerDto myComputerDto = mongoTemplate.findOne(query, MyComputerDto.class, "mycomputer");

        if(myComputerDto.getProfileId().equals(profileId)) {

            if (mongoTemplate.findOne(new Query()
                    .addCriteria(Criteria.where("profileId").is(profileId)
                            .and("myComputers._id").is(computerId)
                            .and("isDeleted").is(true)), MyComputerDto.class) != null) {
                throw new CustomException(CustomErrorCode.CUSTOM_PC_NOT_FOUND);
            }

            Update update = new Update();
            update.set("myComputers.$.isDeleted", true);

            mongoTemplate.updateFirst(query, update, MyComputerDto.class);
        } else throw new CustomException(CustomErrorCode.CAN_NOT_DELETED);
    }
}
