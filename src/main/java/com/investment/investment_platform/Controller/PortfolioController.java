package com.investment.investment_platform.Controller;

import com.investment.investment_platform.dto.PortfolioResponseDTO;
import com.investment.investment_platform.services.InvestmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final InvestmentService investmentService;

    public PortfolioController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<PortfolioResponseDTO>> getPortfolio(@PathVariable Long userId) {

        List<PortfolioResponseDTO> portfolio = investmentService.getUserPortfolio(userId);

        return ResponseEntity.ok(portfolio);
    }
}
