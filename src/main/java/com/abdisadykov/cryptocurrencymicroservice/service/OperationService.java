package com.abdisadykov.cryptocurrencymicroservice.service;

import com.abdisadykov.cryptocurrencymicroservice.dto.OperationDto;
import com.abdisadykov.cryptocurrencymicroservice.exception.OperationNotFoundException;
import com.abdisadykov.cryptocurrencymicroservice.model.Operation;
import com.abdisadykov.cryptocurrencymicroservice.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;

    public String createOperation(String from, String to, Double result) {
        Operation operation = Operation.builder()
                .fromCurrency(from)
                .toCurrency(to)
                .result(result)
                .build();
        operationRepository.save(operation);

        return "Operation saved successfully";
    }

    public OperationDto getOperationById(Long id) {
        Operation operation = operationRepository.findById(id)
                .orElseThrow(()-> new OperationNotFoundException("Operation not found"));
        return mapToOperationDto(operation);
    }

    public List<OperationDto> getAllOperations() {
        return operationRepository.findAll().stream().map(this::mapToOperationDto).toList();
    }

    public Operation mapToOperationEntity(OperationDto operationDto) {
        return Operation.builder()
                .fromCurrency(operationDto.getFromCurrency())
                .toCurrency(operationDto.getToCurrency())
                .result(operationDto.getResult())
                .build();
    }

    public OperationDto mapToOperationDto(Operation operation) {
        return OperationDto.builder()
                .id(operation.getId())
                .fromCurrency(operation.getFromCurrency())
                .toCurrency(operation.getToCurrency())
                .result(operation.getResult())
                .build();
    }

}
