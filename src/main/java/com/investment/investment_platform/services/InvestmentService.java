package com.investment.investment_platform.services;

import com.investment.investment_platform.Specification.InvestmentSpecification;
import com.investment.investment_platform.domain.Fund;
import com.investment.investment_platform.domain.Investment;
import com.investment.investment_platform.domain.User;
import com.investment.investment_platform.dto.*;
import com.investment.investment_platform.exception.UserNotFoundException;
import com.investment.investment_platform.repository.FundRepository;
import com.investment.investment_platform.repository.InvestmentRepository;
import com.investment.investment_platform.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    public List<PortfolioResponseDTO> getUserPortfolio(Long userId) {

        return investmentRepository.getUserPortfolio(userId);

    }

    public List<PortfolioSummaryDTO> getPortfolioSummary(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        return investmentRepository.getPortfolioSummary(userId);
    }

    public Page<InvestmentResponseDTO> getUserInvestments(
            Long userId,
            Pageable pageable
    ) {

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        return investmentRepository.findUserInvestments(userId, pageable);
    }

    public PortfolioValueDTO getPortfolioValue(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        PortfolioValueDTO result = investmentRepository.getPortfolioValue(userId);

        if (result == null) {
            return new PortfolioValueDTO(0.0, 0L);
        }

        return result;
    }

    public Page<InvestmentHistoryDTO> getInvestmentHistory(
            Long userId,
            Long fundId,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    ) {

        Specification<Investment> spec =
                InvestmentSpecification.hasUser(userId)
                        .and(InvestmentSpecification.fetchFund());

        if (fundId != null) {
            spec = spec.and(InvestmentSpecification.hasFund(fundId));
        }

        if (startDate != null) {
            spec = spec.and(InvestmentSpecification.startDate(startDate));
        }

        if (endDate != null) {
            spec = spec.and(InvestmentSpecification.endDate(endDate));
        }

        return investmentRepository.findAll(spec, pageable)
                .map(i -> new InvestmentHistoryDTO(
                        i.getId(),
                        i.getFund().getName(),
                        i.getUnits(),
                        i.getPricePerUnit(),
                        i.getTotalAmount(),
                        i.getCreatedAt()
                ));
    }
}
