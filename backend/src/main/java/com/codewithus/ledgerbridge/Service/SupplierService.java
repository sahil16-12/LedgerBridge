package com.codewithus.ledgerbridge.Service;

import com.codewithus.ledgerbridge.Entity.Bid;
import com.codewithus.ledgerbridge.Entity.Invoice;
import com.codewithus.ledgerbridge.Repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Transactional(readOnly = true)
    public List<Invoice> getInvoicesWithPendingBids(String supplierUsername) {
        // 1) load all invoices for that supplier
        List<Invoice> all = invoiceRepository.findBySupplierusername(supplierUsername);

        // Filter each invoice’s bids down to only the pending ones:
        all.forEach(inv -> {
            List<Bid> pending = inv.getBids().stream()
                    .filter(b -> b.getStatus() == Bid.BidStatus.PENDING)
                    .collect(Collectors.toList());
            inv.setBids(pending);
        });

        // Only keep invoices that still have at least one bid
        return all.stream()
                .filter(inv -> !inv.getBids().isEmpty())
                .toList();
    }
    }


