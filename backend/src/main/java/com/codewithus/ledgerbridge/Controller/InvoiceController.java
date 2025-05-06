package com.codewithus.ledgerbridge.Controller;
import com.codewithus.ledgerbridge.Service.InvoiceService;
import com.codewithus.ledgerbridge.Dto.InvoiceCreateDto;
import com.codewithus.ledgerbridge.Dto.InvoiceDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // 1) Create
    @PostMapping("/create")
    public ResponseEntity<InvoiceDto> createInvoice(
            @Valid @RequestBody InvoiceCreateDto dto) {
        InvoiceDto created = invoiceService.createInvoice(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    // 2) List all for a given username (supplier or buyer)
    @GetMapping("/user/{username}")
    public ResponseEntity<List<InvoiceDto>> listByUser(
            @PathVariable String username) {
        return ResponseEntity.ok(
                invoiceService.getInvoicesByUsername(username)
        );
    }

    // 3) Get one by invoiceId
    @GetMapping("/{invoiceId}")
    public ResponseEntity<InvoiceDto> getByInvoiceId(
            @PathVariable String invoiceId) {
        return ResponseEntity.ok(
                invoiceService.getInvoiceByInvoiceId(invoiceId)
        );
    }
}
