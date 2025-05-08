package com.codewithus.ledgerbridge.Entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Entity
@Table(name = "due_payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DuePayment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String invoiceNumber;


    private Double amount;


    private LocalDate dueDate;


    @Enumerated(EnumType.STRING)
    private PaymentStatus status;


    private String busername;


    private String fusername;


    public enum PaymentStatus {
        UPCOMING,
        OVERDUE,
        PAID
    }
}
