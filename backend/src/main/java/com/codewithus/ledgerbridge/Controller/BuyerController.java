package com.codewithus.ledgerbridge.Controller;
import com.codewithus.ledgerbridge.Dto.BuyerDto;
import com.codewithus.ledgerbridge.Repository.BuyerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buyers")
public class BuyerController {

    private final BuyerRepository buyerRepo;

    public BuyerController(BuyerRepository buyerRepo) {
        this.buyerRepo = buyerRepo;
    }

    @GetMapping
    public List<BuyerDto> getAllBuyers() {
        return buyerRepo.findAll()
                .stream()
                .map(b -> new BuyerDto(
                        b.getId(),
                        b.getUserName(),
                        b.getCompanyName()
                ))
                .collect(Collectors.toList());
    }
}
