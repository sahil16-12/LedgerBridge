package com.codewithus.ledgerbridge.Controller;
import com.codewithus.ledgerbridge.Dto.*;
import com.codewithus.ledgerbridge.Service.LoginService;
import com.codewithus.ledgerbridge.Service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final RegistrationService regService;
    private final LoginService loginService;
    public AuthController(RegistrationService rs, LoginService ls) {
        this.regService = rs;
        this.loginService = ls;
    }

    @PostMapping("/register/supplier")
    public ResponseEntity<?> supplierSignup(@RequestBody SupplierRegistrationDto dto) {
        try {
            String uname = regService.registerSupplier(dto);
            return ResponseEntity.ok(Map.of(
                    "message", "Supplier registered successfully",
                    "username", uname
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "error", "Something went wrong"
            ));
        }
    }

    @GetMapping("/check/supplier/pan")
    public ResponseEntity<?> checkSupplierPan(@RequestParam String pan) {
        boolean exists = regService.isSupplierPanExists(pan);
        return ResponseEntity.ok(Map.of("exists", exists));
    }

    @GetMapping("/check/supplier/phone")
    public ResponseEntity<?> checkSupplierPhone(@RequestParam String phone) {
        boolean exists = regService.isSupplierMobileExists(phone);
        return ResponseEntity.ok(Map.of("exists", exists));
    }

    @PostMapping("/register/buyer")
    public ResponseEntity<?> buyerSignup(@RequestBody BuyerRegistrationDto dto) {
        try {
            String uname = regService.registerBuyer(dto);
            return ResponseEntity.ok(Map.of(
                    "message", "Buyer registered successfully",
                    "username", uname
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "error", "Something went wrong"
            ));
        }
    }
    @GetMapping("/check/buyer/pan")
    public ResponseEntity<?> checkBuyerPan(@RequestParam String pan) {
        boolean exists = regService.isBuyerPanExists(pan);
        return ResponseEntity.ok(Map.of("exists", exists));
    }

    @GetMapping("/check/buyer/phone")
    public ResponseEntity<?> checkBuyerPhone(@RequestParam String phone) {
        boolean exists = regService.isBuyerMobileExists(phone);
        System.out.println("Mobile " + phone);
        System.out.println("Exists " + exists);
        return ResponseEntity.ok(Map.of("exists", exists));
    }
    @PostMapping("/register/financier")
    public ResponseEntity<?> financierSignup(@RequestBody FinancierRegistrationDto dto) {
        try {
            String uname = regService.registerFinancier(dto);
            return ResponseEntity.ok(Map.of(
                    "message", "Financier registered successfully",
                    "username", uname
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "error", "Something went wrong"
            ));
        }
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> adminSignup(@RequestBody AdminRegistrationDto dto) {
        try {
            regService.registerAdmin(dto);
            return ResponseEntity.ok(Map.of(
                    "message", "Admin registered successfully"
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "error", "Something went wrong"
            ));
        }
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        try {
            Object user = loginService.authenticate(dto);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
