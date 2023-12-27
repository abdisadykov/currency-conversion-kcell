package com.abdisadykov.cryptocurrencymicroservice.repository;

import com.abdisadykov.cryptocurrencymicroservice.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
