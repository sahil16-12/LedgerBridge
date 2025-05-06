
package com.codewithus.ledgerbridge.Service.impl;

import com.codewithus.ledgerbridge.Service.InvoiceService;
import com.codewithus.ledgerbridge.Dto.InvoiceCreateDto;
import com.codewithus.ledgerbridge.Dto.InvoiceDto;
import com.codewithus.ledgerbridge.Entity.Invoice;
import com.codewithus.ledgerbridge.Repository.InvoiceRepository;
import com.codewithus.ledgerbridge.helper.InvoiceIdGenerator;
import com.codewithus.ledgerbridge.helper.InvoiceMapper;
import com.codewithus.ledgerbridge.helper.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceIdGenerator invoiceIdGenerator;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceIdGenerator invoiceIdGenerator) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceIdGenerator = invoiceIdGenerator;

    }

    public List<Invoice> getAllApprovedInvoices() {
        return invoiceRepository.findByStatus(Invoice.InvoiceStatus.APPROVED);
    }

    @Override
    public InvoiceDto createInvoice(InvoiceCreateDto dto) {
        String invoiceId = invoiceIdGenerator.generateUniqueInvoiceId(dto.getSupplierusername());
        dto.setInvoiceId(invoiceId);

        Invoice saved = invoiceRepository.save(InvoiceMapper.toEntity(dto));
        return InvoiceMapper.toDto(saved);
    }


    @Override
    public List<InvoiceDto> getInvoicesByUsername(String username) {
        List<Invoice> invoices = invoiceRepository
                .findBySupplierusernameOrBuyerusername(username, username);
        return invoices.stream()
                .map(InvoiceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDto getInvoiceByInvoiceId(String invoiceId) {
        Invoice inv = invoiceRepository.findByInvoiceId(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice", "invoiceId", invoiceId));
        return InvoiceMapper.toDto(inv);
    }
}
