package com.codewithus.ledgerbridge.Service;

import com.codewithus.ledgerbridge.Dto.*;
import com.codewithus.ledgerbridge.Entity.*;
import com.codewithus.ledgerbridge.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final SupplierRepository supplierRepo;
    private final BuyerRepository buyerRepo;
    private final FinancierRepository financierRepo;
    private final AdminRepository adminRepo;
    private final BCryptPasswordEncoder encoder;
    private final UserNameGenerator userNameGenerator;
    public RegistrationService(SupplierRepository s, BuyerRepository b,
                               FinancierRepository f, AdminRepository a,
                               BCryptPasswordEncoder enc, UserNameGenerator userNameGenerator) {
        this.supplierRepo = s;
        this.buyerRepo    = b;
        this.financierRepo= f;
        this.adminRepo    = a;
        this.encoder      = enc;
        this.userNameGenerator = userNameGenerator;
    }

    public String registerSupplier(SupplierRegistrationDto dto) {
        if (supplierRepo.existsByBusinessPan(dto.getBusinessPan())) {
            throw new IllegalArgumentException("PAN already registered");
        }
        Supplier s = new Supplier();
        s.setBusinessPan(dto.getBusinessPan());
        s.setMobile(dto.getMobile());
        s.setBusinessName(dto.getBusinessName());
        s.setGstin(dto.getGstin());
        s.setRegisteredAddress(dto.getRegisteredAddress());
        s.setContactName(dto.getContactName());
        s.setContactDesignation(dto.getContactDesignation());
        s.setContactEmail(dto.getContactEmail());
        s.setMobile(dto.getMobile());
        s.setAccountNumber(dto.getAccountNumber());
        s.setIndustrySector(dto.getIndustrySector());
        s.setBankName(dto.getBankName());
        s.setIfsc(dto.getIfsc());
        s.setEntityType(dto.getEntityType());
        s.setUserName(userNameGenerator.generateUniqueUsername());
        s.setPassword(encoder.encode(dto.getPassword()));
        supplierRepo.save(s);
        return s.getUserName();
    }

    public String registerBuyer(BuyerRegistrationDto dto) {
        if (buyerRepo.existsByBuyerPan(dto.getBuyerPan())) {
            throw new IllegalArgumentException("PAN already registered");
        }
        Buyer b = new Buyer();
        b.setBuyerPan(dto.getBuyerPan());
        b.setGstin(dto.getGstin());
        b.setMobile(dto.getMobile());
        b.setCompanyName(dto.getCompanyName());
        b.setRegisteredAddress(dto.getRegisteredAddress());
        b.setContactName(dto.getContactName());
        b.setContactDesignation(dto.getContactDesignation());
        b.setContactEmail(dto.getContactEmail());
        b.setContactPhone(dto.getContactPhone());
        b.setIndustrySector(dto.getIndustrySector());
        b.setTurnoverBracket(dto.getTurnoverBracket());
        b.setDesiredCreditLimit(dto.getDesiredCreditLimit());
        b.setAccountNumber(dto.getAccountNumber());
        b.setBankName(dto.getBankName());
        b.setIfsc(dto.getIfsc());
        b.setUserName(userNameGenerator.generateUniqueUsername());
        b.setPassword(encoder.encode(dto.getPassword()));
        buyerRepo.save(b);
        return b.getUserName();
    }

    public String registerFinancier(FinancierRegistrationDto dto) {
        if (financierRepo.existsByInstitutionPan(dto.getInstitutionPan())) {
            throw new IllegalArgumentException("PAN already registered");
        }
        Financier f = new Financier();
        f.setInstitutionPan(dto.getInstitutionPan());
        f.setMobile(dto.getMobile());
        f.setInstitutionName(dto.getInstitutionName());
        f.setRbiLicenseNumber(dto.getRbiLicenseNumber());
        f.setContactName(dto.getContactName());
        f.setContactDesignation(dto.getContactDesignation());
        f.setContactEmail(dto.getContactEmail());
        f.setContactPhone(dto.getContactPhone());
        f.setAccountNumber(dto.getAccountNumber());
        f.setBankName(dto.getBankName());
        f.setIfsc(dto.getIfsc());
        f.setRiskAppetite(dto.getRiskAppetite());
        f.setCreditLimitPerSupplier(dto.getCreditLimitPerSupplier());
        f.setPassword(encoder.encode(dto.getPassword()));
        f.setUserName(userNameGenerator.generateUniqueUsername());
        financierRepo.save(f);
        return f.getUserName();
    }

    public void registerAdmin(AdminRegistrationDto dto) {
        if (adminRepo.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }
        Admin a = new Admin();
        a.setFullName(dto.getFullName());
        a.setEmail(dto.getEmail());
        a.setUsername(dto.getUsername());
        a.setRole(dto.getRole());
        a.setPassword(encoder.encode(dto.getPassword()));
        adminRepo.save(a);
    }
}
