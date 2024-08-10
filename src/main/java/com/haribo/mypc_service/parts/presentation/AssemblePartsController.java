package com.haribo.mypc_service.parts.presentation;

import com.haribo.mypc_service.common.exception.CustomErrorCode;
import com.haribo.mypc_service.common.exception.CustomException;
import com.haribo.mypc_service.parts.application.service.PartsService;
import com.haribo.mypc_service.parts.presentation.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/parts")
@RequiredArgsConstructor
public class AssemblePartsController {

    private final PartsService partsService;

    @GetMapping
    private ResponseEntity<? extends PartsResponse> findParts(@RequestParam String partsName) {
        log.info("{} 부품 리스트 요청", partsName);

        return new ResponseEntity<>(partsService.getAllParts(partsName, getPartType(partsName)), HttpStatus.OK);
    }

    private Class<? extends PartsResponse> getPartType(String partsName) {
        return switch (partsName) {
            case "cpu" -> CpuResponse.class;
            case "memory" -> MemoryResponse.class;
            case "case" -> CaseResponse.class;
            case "coolerTuning" -> CoolerResponse.class;
            case "hdd" -> HddResponse.class;
            case "ssd" -> SsdResponse.class;
            case "power" -> PowerResponse.class;
            case "motherboard" -> MotherboardResponse.class;
            case "gpu" -> GpuResponse.class;
            default -> throw new CustomException(CustomErrorCode.PART_NOT_FOUND);
        };
    }
}
