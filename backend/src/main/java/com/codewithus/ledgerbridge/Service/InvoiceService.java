package com.codewithus.ledgerbridge.Service;

import com.codewithus.ledgerbridge.Dto.InvoiceCreateDto;
import com.codewithus.ledgerbridge.Dto.InvoiceDto;
import com.codewithus.ledgerbridge.Entity.Invoice;

import java.util.List;

public interface InvoiceService {
    InvoiceDto createInvoice(InvoiceCreateDto dto);
    List<InvoiceDto> getInvoicesByUsername(String username);
    InvoiceDto getInvoiceByInvoiceId(String invoiceId);
    List<Invoice> getAllApprovedInvoices();
}
