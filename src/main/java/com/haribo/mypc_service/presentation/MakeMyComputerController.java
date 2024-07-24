package com.haribo.mypc_service.presentation;

import com.haribo.mypc_service.application.dto.MyComputerDto;
import com.haribo.mypc_service.application.service.MyComputerService;
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

    @PostMapping
    @ResponseBody
    private ResponseEntity<?> addMyComputer(@RequestBody MyComputerDto myComputerDto){
        try {
            myComputerService.addMyComputer(myComputerDto);
        } catch (RuntimeException e) {
            // 5개 꽉 찬 경우에는 computer 못만들어요 에러
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(myComputerDto);
    }

    @GetMapping
    @ResponseBody
    private ResponseEntity<?> findMyComputer(@RequestParam("userId") String userId){
        List<MyComputerDto> myComputerList = null;
        try {
            myComputerList = myComputerService.getMyComputerList(userId);
        } catch(RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(myComputerService.getMyComputerList(userId));
    }
}