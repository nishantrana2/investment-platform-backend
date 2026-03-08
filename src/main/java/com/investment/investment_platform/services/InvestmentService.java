package com.investment.investment_platform.services;

import com.investment.investment_platform.domain.Fund;
import com.investment.investment_platform.domain.Investment;
import com.investment.investment_platform.domain.User;
import com.investment.investment_platform.dto.CreateInvestmentRequest;
import com.investment.investment_platform.exception.UserNotFoundException;
import com.investment.investment_platform.repository.FundRepository;
import com.investment.investment_platform.repository.InvestmentRepository;
import com.investment.investment_platform.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InvestmentService {

    private final UserRepository userRepository;
    private final FundRepository fundRepository;
    private final InvestmentRepository investmentRepository;

    public InvestmentService(InvestmentRepository investmentRepository, UserRepository userRepository , FundRepository fundRepository){
        this.investmentRepository = investmentRepository;
        this.userRepository = userRepository;
        this.fundRepository = fundRepository;
    }

    @Transactional
    public void invest(CreateInvestmentRequest request){

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

        Fund fund = fundRepository.findById(request.getFundId())
                .orElseThrow(() -> new RuntimeException("Fund not found"));

        if(!fund.isActive()){
            throw new RuntimeException("Fund inactive");
        }

        if(fund.getTotalUnitsAvailable() < request.getUnits()){
            throw new RuntimeException("Not enough units available");
        }

        fund.setTotalUnitsAvailable(
                fund.getTotalUnitsAvailable() - request.getUnits()
        );

        Investment investment = new Investment(
                user,
                fund,
                request.getUnits(),
                fund.getPricePerUnit()
        );

        investmentRepository.save(investment);

    }
}
