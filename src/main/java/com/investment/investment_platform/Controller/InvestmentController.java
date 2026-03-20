package com.investment.investment_platform.Controller;

import com.investment.investment_platform.dto.*;
import com.investment.investment_platform.services.InvestmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/investments")
public class InvestmentController {

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService){
        this.investmentService = investmentService;
    }


    @PostMapping
    public ResponseEntity<Void> invest(
            @RequestBody CreateInvestmentRequest request
    ){

        investmentService.invest(request);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{userId}/portfolio/summary")
    public ResponseEntity<List<PortfolioSummaryDTO>> getPortfolioSummary(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                investmentService.getPortfolioSummary(userId)
        );
    }

    @GetMapping("/users/{userId}/investmentsList")
    public ResponseEntity<Page<InvestmentResponseDTO>> getUserInvestments(
            @PathVariable Long userId,
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                investmentService.getUserInvestments(userId, pageable)
        );
    }

    @GetMapping("/users/{userId}/portfolio/value")
    public ResponseEntity<PortfolioValueDTO> getPortfolioValue(
            @PathVariable Long userId
    ) {

        return ResponseEntity.ok(
                investmentService.getPortfolioValue(userId)
        );
    }

    @GetMapping("/users/{userId}/investments")
    public ResponseEntity<Page<InvestmentHistoryDTO>> getInvestmentHistory(
            @PathVariable Long userId,
            @RequestParam(required = false) Long fundId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                investmentService.getInvestmentHistory(
                        userId,
                        fundId,
                        startDate,
                        endDate,
                        pageable
                )
        );
    }
}
