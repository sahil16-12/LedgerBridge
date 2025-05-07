package com.codewithus.ledgerbridge.Controller;
import com.codewithus.ledgerbridge.Dto.*;
import com.codewithus.ledgerbridge.Service.*;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final RegistrationService regService;
    private final LoginService loginService;
    private final SupplierMailOtpService mailOtpService;
    private  final FinancierMailOtpService financierMailOtpService;
    private  final BuyerMailOtpService buyerMailOtpService;
    public AuthController(RegistrationService rs, LoginService ls, SupplierMailOtpService ms, FinancierMailOtpService fms, BuyerMailOtpService bms) {
        this.regService = rs;
        this.loginService = ls;
        this.mailOtpService = ms;
        this.financierMailOtpService = fms;
        this.buyerMailOtpService = bms;

    }

    @PostMapping("/register/supplier")
    public ResponseEntity<?> registerSupplier(
            @Validated @RequestBody SupplierRegistrationDto dto
    ) throws MessagingException {
        String activationToken = mailOtpService.initiateRegistration(dto);
        return ResponseEntity.ok(Map.of(
                "message", "OTP sent to your email",
                "activationToken", activationToken
        ));
    }
    @PostMapping("/verify/supplier")
    public ResponseEntity<?> verifySupplier(
            @Validated @RequestBody VerifyDto dto
    ) {
        String username = regService.verifySupplier(
                dto.getActivationToken(),
                dto.getOtp()
        );
        return ResponseEntity.ok(Map.of(
                "message", "Supplier registered",
                "username", username
        ));
    }

    @PostMapping("/register/buyer")
    public ResponseEntity<?> registerBuyer(@Validated @RequestBody BuyerRegistrationDto dto) throws MessagingException {
        String activationToken = buyerMailOtpService.initiateRegistration(dto);
        return ResponseEntity.ok(Map.of(
                "message", "OTP sent to your email",
                "activationToken", activationToken
        ));
    }

    @PostMapping("/verify/buyer")
    public ResponseEntity<?> verifyBuyer(@Validated @RequestBody VerifyDto dto) {
        String username = regService.verifyBuyer(dto.getActivationToken(), dto.getOtp());
        return ResponseEntity.ok(Map.of(
                "message", "Buyer registered",
                "username", username
        ));
    }


    @PostMapping("/register/financier")
    public ResponseEntity<?> registerFinancier(@Validated @RequestBody FinancierRegistrationDto dto) throws MessagingException {
        String activationToken = financierMailOtpService.initiateRegistration(dto);
        return ResponseEntity.ok(Map.of(
                "message", "OTP sent to your email",
                "activationToken", activationToken
        ));
    }

    @PostMapping("/verify/financier")
    public ResponseEntity<?> verifyFinancier(@Validated @RequestBody VerifyDto dto) {
        String username = regService.verifyFinancier(dto.getActivationToken(), dto.getOtp());
        return ResponseEntity.ok(Map.of(
                "message", "Financier registered",
                "username", username
        ));
    }


    @PostMapping("/register/admin")
    public ResponseEntity<?> adminSignup(@RequestBody AdminRegistrationDto dto) {
        regService.registerAdmin(dto);
        return ResponseEntity.ok("Admin registered");
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
