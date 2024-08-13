package com.haribo.mypc_service.custompc.presentation;

import com.haribo.mypc_service.custompc.application.service.MyComputerService;
import com.haribo.mypc_service.custompc.presentation.request.MyComputerRequest;
import com.haribo.mypc_service.custompc.presentation.response.MyComputerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pc")
@RequiredArgsConstructor
public class MakeMyComputerController {

    private final MyComputerService myComputerService;

    @PostMapping("/{profileId}")
    private ResponseEntity<?> addMyComputer(@RequestBody MyComputerRequest myComputer, @PathVariable String profileId){

        myComputerService.addMyComputer(myComputer, profileId);

        return ResponseEntity.status(HttpStatus.CREATED).body(myComputer);
    }

    @GetMapping
    private ResponseEntity<?> findMyComputer(@RequestParam("profileId") String profileId){

        List<MyComputerResponse> myComputerList = myComputerService.getMyComputerList(profileId);

        return ResponseEntity.status(HttpStatus.OK).body(myComputerList);
    }

    @PatchMapping("/{profileId}")
    private ResponseEntity<?> updateMyComputer(@PathVariable String profileId, @RequestBody MyComputerRequest myComputer) {

        myComputerService.updateMyComputerDto(profileId, myComputer);

        return ResponseEntity.status(HttpStatus.OK).body("업데이트 완료!");
    }

    @DeleteMapping("/{profileId}/{computerId}")
    private ResponseEntity<?> deleteMyComputer(@PathVariable String profileId, @PathVariable String computerId) {

        myComputerService.deleteMyComputerDto(profileId, computerId);

        return ResponseEntity.status(HttpStatus.OK).body("삭제 완료!");
    }
}
