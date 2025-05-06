
package com.codewithus.ledgerbridge.helper;

import com.codewithus.ledgerbridge.Dto.InvoiceCreateDto;
import com.codewithus.ledgerbridge.Dto.InvoiceDto;
import com.codewithus.ledgerbridge.Entity.Invoice;

public class InvoiceMapper {

    public static Invoice toEntity(InvoiceCreateDto dto) {
        return Invoice.builder()
                .invoiceId(dto.getInvoiceId())
                .supplierusername(dto.getSupplierusername())
                .buyerusername(dto.getBuyerusername())
                .amount(dto.getAmount())
                .dueDate(dto.getDueDate())
                .uploadDate(dto.getUploadDate())
                .remarks(dto.getRemarks())
                .invoiceDocumentUrl(dto.getInvoiceDocumentUrl())
                // default new invoices to PENDING
                .status(Invoice.InvoiceStatus.PENDING)
                .build();
    }

    public static InvoiceDto toDto(Invoice inv) {
        return InvoiceDto.builder()
                .id(inv.getId())
                .invoiceId(inv.getInvoiceId())
                .supplierusername(inv.getSupplierusername())
                .buyerusername(inv.getBuyerusername())
                .amount(inv.getAmount())
                .dueDate(inv.getDueDate())
                .uploadDate(inv.getUploadDate())
                .status(inv.getStatus())
                .remarks(inv.getRemarks())
                .invoiceDocumentUrl(inv.getInvoiceDocumentUrl())
                .approvedDate(inv.getApprovedDate())
                .approvedBy(inv.getApprovedBy())
                .build();
    }
}
