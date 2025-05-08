import lombok.Data;
package com.codewithus.ledgerbridge.Dto;
@Data
class PaymentHistoryDto {
    private String month;
    private double amount;
}