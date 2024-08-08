package com.haribo.mypc_service.custompc.presentation;

import com.haribo.mypc_service.custompc.application.service.MyComputerService;
import com.haribo.mypc_service.custompc.presentation.request.MyComputerRequest;
import com.haribo.mypc_service.custompc.presentation.response.MyComputerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pc")
public class MakeMyComputerController {
    @Autowired
    private MyComputerService myComputerService;

    @PostMapping("/{userId}")
    @ResponseBody
    private ResponseEntity<?> addMyComputer(@RequestBody MyComputerRequest myComputer, @PathVariable String userId){
        try {
            myComputerService.addMyComputer(myComputer, userId);
        } catch (RuntimeException e) {
            // 5개 꽉 찬 경우에는 computer 못만들어요 에러
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(myComputer);
    }

    @GetMapping
    @ResponseBody
    private ResponseEntity<?> findMyComputer(@RequestParam("userId") String userId){
        List<MyComputerResponse> myComputerList = null;
        try {
            myComputerList = myComputerService.getMyComputerList(userId);
        } catch(RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK).body(myComputerList);
    }

    @PatchMapping("/{userId}")
    private ResponseEntity<?> updateMyComputer(@PathVariable String userId, @RequestBody MyComputerRequest myComputer) {
        try{
            myComputerService.updateMyComputerDto(userId, myComputer);
        } catch(RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK).body("업데이트 완료!");
    }

    @DeleteMapping("/{userId}/{computerId}")
    private ResponseEntity<?> deleteMyComputer(@PathVariable String userId, @PathVariable String computerId) {
        try{
            myComputerService.deleteMyComputerDto(userId, computerId);
        } catch(RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK).body("삭제 완료!");
    }
}
