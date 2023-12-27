package com.abdisadykov.cryptocurrencymicroservice.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationDto {
    private Long id;
    private String fromCurrency;
    private String toCurrency;
    private Double result;

}
