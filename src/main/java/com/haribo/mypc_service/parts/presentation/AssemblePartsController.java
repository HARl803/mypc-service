package com.haribo.mypc_service.parts.presentation;

import com.haribo.mypc_service.parts.application.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parts")
public class AssemblePartsController {

    @Autowired
    private PartsService partsService;

    @GetMapping
    @ResponseBody
    private ResponseEntity<?> findParts(@RequestParam("partsName") String partsName){

        return ResponseEntity.status(HttpStatus.OK).body(partsService.getAllParts(partsName));
    }
}
