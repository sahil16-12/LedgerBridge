package com.codewithus.ledgerbridge.Dto;

import lombok.Data;

@Data
class PendingInvoiceDto {
    private String id;
    private String seller;
    private String amount;
    private String dueDate;
    private String status;
}
