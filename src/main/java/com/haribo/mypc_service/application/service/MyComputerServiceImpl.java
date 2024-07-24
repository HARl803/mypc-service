package com.haribo.mypc_service.application.service;

import com.haribo.mypc_service.application.dto.MyComputerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MyComputerServiceImpl implements MyComputerService {

    private final MongoTemplate mongoTemplate;

    @Override
    public int countMyComputer(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId)
                .and("isDelete").is(false));

        return (int) mongoTemplate.count(query, MyComputerDto.class);
    }

    @Override
    public MyComputerDto addMyComputer(MyComputerDto myComputerDto){
        String userId = myComputerDto.getUserId();
        myComputerDto.setIsDelete(false);

        if(countMyComputer(userId)>=5)
            throw new RuntimeException("커스텀 PC 갯수가 5개로 꽉차서 더이상 만들 수 없어요!");

        return mongoTemplate.save(myComputerDto);
    }

    @Override
    public List<MyComputerDto> getMyComputerList(String userId){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("userId").is(userId)
                        .and("isDelete").is(false))
        );

        AggregationResults<MyComputerDto> results = mongoTemplate.aggregate(aggregation, "mycomputer", MyComputerDto.class);

        if(results.getMappedResults().size() > 0){
            return results.getMappedResults();
        } else throw new RuntimeException("유저의 커스텀 PC 목록이 텅~ 비어있네요!");
    }

    @Override
    public MyComputerDto getMyComputerDto(String id){
        MyComputerDto myComputerDto = mongoTemplate.findById(id, MyComputerDto.class);

        if(myComputerDto==null) throw new RuntimeException("잘못된 ID 값입니다.");

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id)
                .and("isDelete").is(false));

        if(!mongoTemplate.exists(query, MyComputerDto.class)) {
            throw new RuntimeException("내 PC에 해당 이름을 가진 컴퓨터가 없는데요..?");
        }

        return mongoTemplate.findOne(query, MyComputerDto.class);
    }


    @Override
    public void deleteMyComputerDto(String id){
        MyComputerDto myComputerDto = getMyComputerDto(id);
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        mongoTemplate.updateFirst(query, Update.update("isDelete", true), MyComputerDto.class);
    }

    @Override
    public MyComputerDto updateMyComputerDto(String id, MyComputerDto myNewComputerDto) {
        MyComputerDto myComputerDto = getMyComputerDto(id);
        myNewComputerDto.setId(myComputerDto.getId());

        // 프론트에서 못가져올 경우 대비.
        myNewComputerDto.setIsDelete(false);
        myNewComputerDto.setUserId(myComputerDto.getUserId());

        return mongoTemplate.save(myNewComputerDto);
    }
}