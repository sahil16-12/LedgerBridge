package com.codewithus.ledgerbridge.Controller;


import com.codewithus.ledgerbridge.Entity.DuePayment;
import com.codewithus.ledgerbridge.Repository.DuePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/due-payments")
public class DuePaymentController {


    @Autowired
    private DuePaymentRepository duePaymentRepository;


    // Get all due payments
    @GetMapping
    public List<DuePayment> getAllDuePayments() {
        return duePaymentRepository.findAll();
    }


    // Get a specific due payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<DuePayment> getDuePaymentById(@PathVariable Long id) {
        Optional<DuePayment> duePayment = duePaymentRepository.findById(id);
        return duePayment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    // Create a new due payment
    @PostMapping
    public ResponseEntity<DuePayment> createDuePayment(@RequestBody DuePayment duePayment) {
        DuePayment createdDuePayment = duePaymentRepository.save(duePayment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDuePayment);
    }


    // Update an existing due payment
    @PutMapping("/{id}")
    public ResponseEntity<DuePayment> updateDuePayment(@PathVariable Long id, @RequestBody DuePayment duePayment) {
        if (duePaymentRepository.existsById(id)) {
            duePayment.setId(id);  // Set the existing ID for update
            DuePayment updatedDuePayment = duePaymentRepository.save(duePayment);
            return ResponseEntity.ok(updatedDuePayment);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    // Delete a due payment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDuePayment(@PathVariable Long id) {
        if (duePaymentRepository.existsById(id)) {
            duePaymentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    // Get due payments for a specific buyer
    @GetMapping("/buyer/{buyerUsername}")
    public List<DuePayment> getDuePaymentsByBuyer(@PathVariable String buyerUsername) {
        return duePaymentRepository.findByBusername(buyerUsername);
    }


    // Get due payments for a specific financier
    @GetMapping("/financier/{financierUsername}")
    public List<DuePayment> getDuePaymentsByFinancier(@PathVariable String financierUsername) {
        return duePaymentRepository.findByFusername(financierUsername);
    }


    // Buyer makes a payment
    @PatchMapping("/{id}/pay")
    public ResponseEntity<?> payDuePayment(@PathVariable Long id) {
        Optional<DuePayment> optionalDuePayment = duePaymentRepository.findById(id);


        if (optionalDuePayment.isPresent()) {
            DuePayment duePayment = optionalDuePayment.get();


            // Check if the payment status is not already PAID
            if (duePayment.getStatus() != DuePayment.PaymentStatus.PAID) {
                duePayment.setStatus(DuePayment.PaymentStatus.PAID);  // Mark as paid
                duePayment.setDueDate(LocalDate.now());  // Optionally update the payment date (or transaction date)
                duePaymentRepository.save(duePayment);  // Save the updated due payment
                return ResponseEntity.ok(duePayment);  // Return the updated due payment
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Payment has already been made for this invoice.");
            }
        }


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Due payment not found.");
    }
}
