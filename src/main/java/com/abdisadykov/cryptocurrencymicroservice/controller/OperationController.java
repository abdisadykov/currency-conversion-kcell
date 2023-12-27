package com.abdisadykov.cryptocurrencymicroservice.controller;

import com.abdisadykov.cryptocurrencymicroservice.dto.OperationDto;
import com.abdisadykov.cryptocurrencymicroservice.model.Operation;
import com.abdisadykov.cryptocurrencymicroservice.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @GetMapping("/{id}")
    public ResponseEntity<OperationDto> getOperation(@PathVariable Long id) {
        OperationDto optionalOperation = operationService.getOperationById(id);
        return ResponseEntity.ok(optionalOperation);
    }

    @GetMapping
    public ResponseEntity<List<OperationDto>> getAllOperations() {
        List<OperationDto> operations = operationService.getAllOperations();
        return ResponseEntity.ok(operations);
    }

}
