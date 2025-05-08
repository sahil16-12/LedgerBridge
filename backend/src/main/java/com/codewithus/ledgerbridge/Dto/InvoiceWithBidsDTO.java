package com.codewithus.ledgerbridge.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record InvoiceWithBidsDTO(
        Long      id,
        String    invoiceId,
        String    supplierUsername,
        String    buyerUsername,
        BigDecimal amount,
        LocalDate dueDate,
        List<BiddDTO> bids
) {}
