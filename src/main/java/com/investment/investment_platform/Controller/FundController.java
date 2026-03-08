package com.investment.investment_platform.Controller;

import com.investment.investment_platform.domain.Fund;
import com.investment.investment_platform.dto.FundResponseDTO;
import com.investment.investment_platform.services.FundService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/funds")
public class FundController {

    private final FundService fundService;

    public FundController(FundService fundService){
        this.fundService = fundService;
    }

    @PostMapping
    public ResponseEntity<FundResponseDTO> createFund(@RequestBody Fund fund) {
        return ResponseEntity.ok(fundService.createFund(fund));
    }

    @GetMapping
    public ResponseEntity<List<FundResponseDTO>> getAllFunds() {
        return ResponseEntity.ok(fundService.getAllFunds());
    }

}
