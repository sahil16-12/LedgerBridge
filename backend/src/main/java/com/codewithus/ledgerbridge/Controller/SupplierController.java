package com.codewithus.ledgerbridge.Controller;


import com.codewithus.ledgerbridge.Dto.BiddDTO;
import com.codewithus.ledgerbridge.Dto.InvoiceWithBidsDTO;
import com.codewithus.ledgerbridge.Entity.Bid;
import com.codewithus.ledgerbridge.Entity.Invoice;
import com.codewithus.ledgerbridge.Entity.Notification;
import com.codewithus.ledgerbridge.Entity.Transaction;
import com.codewithus.ledgerbridge.Repository.BidRepository;
import com.codewithus.ledgerbridge.Repository.InvoiceRepository;
import com.codewithus.ledgerbridge.Repository.NotificationRepository;
import com.codewithus.ledgerbridge.Repository.TransactionRepository;
import com.codewithus.ledgerbridge.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/supplier")
public class SupplierController {


    @Autowired
    private BidRepository bidRepository;


    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private SupplierService supplierService;


    // ✅ Approve or Reject a Bid
    @PutMapping("/bids/{bidId}/status")
    public ResponseEntity<?> updateBidStatus(
            @PathVariable Long bidId,
            @RequestParam Bid.BidStatus status,
            @RequestParam String supplierUsername) {


        Optional<Bid> bidOpt = bidRepository.findById(bidId);


        if (bidOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bid not found.");
        }


        Bid bid = bidOpt.get();
        Invoice invoice = bid.getInvoice();


        // Ensure supplier is authorized to approve/reject
        if (!invoice.getSupplierusername().equals(supplierUsername)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized: Not your invoice.");
        }


        if (bid.getStatus() != Bid.BidStatus.PENDING) {
            return ResponseEntity.badRequest().body("Bid already " + bid.getStatus());
        }


        Transaction transaction = null;


        // Allow only one accepted bid per invoice
        if (status == Bid.BidStatus.ACCEPTED) {
            Optional<Bid> accepted = bidRepository.findByInvoiceId(invoice.getId())
                    .stream()
                    .filter(b -> b.getStatus() == Bid.BidStatus.ACCEPTED)
                    .findFirst();


            if (accepted.isPresent()) {
                return ResponseEntity.badRequest().body("An accepted bid already exists for this invoice.");
            }


            // Reject other bids
            bidRepository.findByInvoiceId(invoice.getId())
                    .forEach(b -> {
                        if (b.getStatus() != Bid.BidStatus.ACCEPTED) {
                            b.setStatus(Bid.BidStatus.REJECTED);
                            bidRepository.save(b);
                        }
                    });


            bid.setStatus(Bid.BidStatus.ACCEPTED);
            String creditedTo = invoice.isFactoring() ? invoice.getSupplierusername() : invoice.getBuyerusername();
            bid.setCreditedTo(creditedTo);


            Notification notification = Notification.builder()
                    .recipientUsername(bid.getFinancier().getUserName())
                    .message("Your bid for Invoice ID " + invoice.getId() + " has been accepted by the supplier.")
                    .seen(false)
                    .createdAt(LocalDateTime.now())
                    .build();
            notificationRepository.save(notification);


            if (!invoice.isFactoring()) {
                Notification buyerNotification = Notification.builder()
                        .recipientUsername(invoice.getBuyerusername())
                        .message("Financier " + bid.getFinancier().getUserName() +
                                " will pay you amount " + bid.getBidAmount() +
                                " for Invoice ID " + invoice.getInvoiceId())
                        .seen(false)
                        .createdAt(LocalDateTime.now())
                        .build();
                notificationRepository.save(buyerNotification);
            }


            transaction = Transaction.builder()
                    .bidAmount(bid.getBidAmount())
                    .discountRate(bid.getDiscountRate())
                    .creditedTo(creditedTo)
                    .transactionTime(LocalDateTime.now())
                    .notes("Bid accepted and funds credited to " + creditedTo)
                    .build();


            transactionRepository.save(transaction);
            bidRepository.save(bid);


            System.out.println("Log: Crediting " + bid.getBidAmount() + " to " + creditedTo);


        } else if (status == Bid.BidStatus.REJECTED) {
            bid.setStatus(Bid.BidStatus.REJECTED);
            bidRepository.save(bid);
        }


        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/{username}/invoices-with-pending-bids")
    public ResponseEntity<List<Invoice>> getInvoicesWithPendingBids(
            @PathVariable String username) {

        List<Invoice> allInvoices = invoiceRepository.findBySupplierusername(username);

        // Filter invoices that have at least one bid
        List<Invoice> invoicesWithBids = allInvoices.stream()
                .filter(invoice -> invoice.getBids() != null && !invoice.getBids().isEmpty())
                .collect(Collectors.toList());

        // Optionally clear bids from each invoice to avoid sending them in response
        invoicesWithBids.forEach(invoice -> invoice.setBids(null));

        return ResponseEntity.ok(invoicesWithBids);
    }
}