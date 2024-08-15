package com.haribo.mypc_service.custompc.presentation;

import com.haribo.mypc_service.common.auth.AuthService;
import com.haribo.mypc_service.custompc.application.service.MyComputerService;
import com.haribo.mypc_service.custompc.presentation.request.MyComputerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/pc")
@RequiredArgsConstructor
public class MakeMyComputerController {

    private final MyComputerService myComputerService;
    private final AuthService authService;

    @PostMapping
    private ResponseEntity<?> addMyComputer(@RequestBody MyComputerRequest myComputer,
                                            @CookieValue("JSESSIONID") String sessionId) throws URISyntaxException {

        String profileId = authService.authorizedProfileId(sessionId);

        myComputerService.addMyComputer(myComputer, profileId);

        return ResponseEntity.status(HttpStatus.CREATED).body(myComputer);
    }

    @GetMapping
    private ResponseEntity<?> findMyComputer(@CookieValue("JSESSIONID") String sessionId) throws URISyntaxException {

        String profileId = authService.authorizedProfileId(sessionId);

        return ResponseEntity.status(HttpStatus.OK).body(myComputerService.getMyComputerList(profileId));
    }

    @PatchMapping
    private ResponseEntity<?> updateMyComputer(@RequestBody MyComputerRequest myComputer,
                                               @CookieValue("JSESSIONID") String sessionId) throws URISyntaxException {

        String profileId = authService.authorizedProfileId(sessionId);

        myComputerService.updateMyComputerDto(profileId, myComputer);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{computerId}")
    private ResponseEntity<?> deleteMyComputer(@PathVariable String computerId,
                                               @CookieValue("JSESSIONID") String sessionId) throws URISyntaxException {

        String profileId = authService.authorizedProfileId(sessionId);

        myComputerService.deleteMyComputerDto(profileId, computerId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
