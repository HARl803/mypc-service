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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class MyComputerServiceImpl implements MyComputerService {

    private final MongoTemplate mongoTemplate;
    private final RestTemplate restTemplate;

    @Value("${auth.url}")
    private String AUTH_URL;

    @Override
    public int countMyComputer(String profileId) {

        log.info("내 컴퓨터 갯수 세기!");

//        if(!checkProfile(profileId)) throw new CustomException(CustomErrorCode.USER_NOT_FOUND);

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

        if(!checkProfile(profileId)) throw new CustomException(CustomErrorCode.USER_NOT_FOUND);

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

        if(!checkProfile(profileId)) throw new CustomException(CustomErrorCode.USER_NOT_FOUND);

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

//        if(!checkProfile(profileId)) throw new CustomException(CustomErrorCode.USER_NOT_FOUND);

        // 여기서 내가 원하는 computer를 찾았어
        Query query = new Query();
        query.addCriteria(Criteria.where("myComputers._id").is(computerId));

        if(mongoTemplate.findOne(query, MyComputerDto.class)==null){
            throw new CustomException(CustomErrorCode.CUSTOM_PC_NOT_FOUND);
        }

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
    }


    @Override
    public void deleteMyComputerDto(String profileId, String computerId) {

        log.debug("해당 컴퓨터 삭제하기!");

        MyComputerResponse myComputerDto = getMyComputerDto(profileId, computerId);

        log.debug("computerDto의 이름 : {}", myComputerDto.getComputerName());

        Query query = new Query();

        // if(!checkProfile(profileId)) throw new CustomException(CustomErrorCode.USER_NOT_FOUND);

        query.addCriteria(Criteria.where("myComputers._id").is(computerId));
        if(mongoTemplate.findOne(query, MyComputerDto.class)==null){
            throw new CustomException(CustomErrorCode.CUSTOM_PC_NOT_FOUND);
        }

        if(mongoTemplate.findOne(new Query()
                .addCriteria(Criteria.where("profileId").is(profileId)
                .and("myComputers._id").is(computerId)
                .and("isDeleted").is(true)), MyComputerDto.class)!=null){
            throw new CustomException(CustomErrorCode.CUSTOM_PC_NOT_FOUND);
        }

        Update update = new Update();
        update.set("myComputers.$.isDeleted", true);

        mongoTemplate.updateFirst(query, update, MyComputerDto.class);
    }

    private Boolean checkProfile(String profileId) {

        log.info("프로필 아이디 체크하기! : 아직 구현 안되어있어요... auth 어케 갔다와");

        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.ACCEPT, Collections.singletonList(MediaType.APPLICATION_JSON_VALUE));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        URI authUrl = URI.create(AUTH_URL);

        try {
            ResponseEntity<?> map = restTemplate.exchange(authUrl, HttpMethod.GET, entity, LinkedHashMap.class);
            log.debug("auth/profile 요청 시 오는 응답 BODY: {}", map.getBody());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

//        return map.getBody().get("profileId")==profileId;

        log.debug("일단 무조건 성공으로 반환. checkProfile 메서드 수정 필요");
        return true;
    }
}
