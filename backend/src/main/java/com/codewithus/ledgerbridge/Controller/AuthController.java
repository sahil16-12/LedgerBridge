package com.codewithus.ledgerbridge.Controller;
import com.codewithus.ledgerbridge.Dto.*;
import com.codewithus.ledgerbridge.Service.LoginService;
import com.codewithus.ledgerbridge.Service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        String uname=regService.registerSupplier(dto);
        return ResponseEntity.ok("Supplier registered and username is:"+uname);
    }

    @PostMapping("/register/buyer")
    public ResponseEntity<?> buyerSignup(@RequestBody BuyerRegistrationDto dto) {
        String uname=regService.registerBuyer(dto);
        return ResponseEntity.ok("Buyer registered  and username is:"+uname);
    }

    @PostMapping("/register/financier")
    public ResponseEntity<?> financierSignup(@RequestBody FinancierRegistrationDto dto) {
        String uname=regService.registerFinancier(dto);
        return ResponseEntity.ok("Financier registered and username is:"+uname);
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
