package com.haribo.mypc_service.presentation;

import com.haribo.mypc_service.application.service.parts.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parts")
public class AssemblePartsController {

    @Autowired
    private PartsService partsService;

    @GetMapping
    @ResponseBody
    private ResponseEntity<?> findParts(@RequestParam("partsName") String partsName){

        List<Object> partsDtos = partsService.getAllParts(partsName);

        return ResponseEntity.status(HttpStatus.OK).body(partsDtos);
    }
}
