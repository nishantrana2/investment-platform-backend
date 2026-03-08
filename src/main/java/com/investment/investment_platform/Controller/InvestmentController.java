package com.investment.investment_platform.Controller;

import com.investment.investment_platform.dto.CreateInvestmentRequest;
import com.investment.investment_platform.services.InvestmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
