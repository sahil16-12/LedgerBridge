package com.codewithus.ledgerbridge.Dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BiddDTO(
        Long      id,
        BigDecimal bidAmount,
        BigDecimal discountRate,
        String    status,
        LocalDateTime createdAt,
        String    creditedTo
) {}
