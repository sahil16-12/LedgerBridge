package com.codewithus.ledgerbridge.Repository;


import com.codewithus.ledgerbridge.Entity.DuePayment;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface DuePaymentRepository extends JpaRepository<DuePayment, Long> {
    List<DuePayment> findByBusername(String buyerUsername);
    List<DuePayment> findByFusername(String financierUsername);
}
