package com.haribo.mypc_service.application.service;

import com.haribo.mypc_service.application.dto.MyComputerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MyComputerServiceImpl implements MyComputerService {

    private final MongoTemplate mongoTemplate;

    @Override
    public int countMyComputer(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId)
                .and("myComputer.isDelete").is(false));

        return (int) mongoTemplate.count(query, MyComputerDto.class);
    }

    @Override
    public MyComputerDto addMyComputer(MyComputerDto myComputerDto){
        String userId = myComputerDto.getUserId();
        myComputerDto.getMyComputer().setIsDelete(false);

        if(countMyComputer(userId)>=5)
            throw new RuntimeException("생성한 커스텀 PC 갯수가 꽉찼답니다.");

        return mongoTemplate.save(myComputerDto);
    }

    @Override
    public List<MyComputerDto> getMyComputerList(String userId){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("userId").is(userId)
                        .and("myComputer.isDelete").is(false))
        );

        AggregationResults<MyComputerDto> results = mongoTemplate.aggregate(aggregation, "mycomputer", MyComputerDto.class);

        if(results.getMappedResults().size() > 0){
            return results.getMappedResults();
        } else throw new RuntimeException("유저의 커스텀 PC 목록이 텅~ 비어있네요!");
    }

}