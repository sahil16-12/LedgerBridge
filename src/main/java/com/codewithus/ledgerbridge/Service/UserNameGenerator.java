package com.codewithus.ledgerbridge.Service;

import com.codewithus.ledgerbridge.Repository.BuyerRepository;
import com.codewithus.ledgerbridge.Repository.FinancierRepository;
import com.codewithus.ledgerbridge.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserNameGenerator {

    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private FinancierRepository financierRepository;
    @Autowired
    private SupplierRepository supplierRepository;


    @Transactional
    public String generateUniqueUsername() {
        String username;
        do {
            username = "user_" + UUID.randomUUID().toString().substring(0, 8);
        } while (buyerRepository.existsBuyerByUserName(username) || financierRepository.existsFinancierByUserName(username)||supplierRepository.existsSupplierByUserName(username));
        return username;
    }
}

