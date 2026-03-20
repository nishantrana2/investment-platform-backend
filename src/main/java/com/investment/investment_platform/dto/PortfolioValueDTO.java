package com.investment.investment_platform.dto;

public class PortfolioValueDTO {

    private Double totalInvestment;
    private Long totalFunds;

    public PortfolioValueDTO(Double totalInvestment, Long totalFunds) {
        this.totalInvestment = totalInvestment;
        this.totalFunds = totalFunds;
    }

    public Double getTotalInvestment() {
        return totalInvestment;
    }

    public Long getTotalFunds() {
        return totalFunds;
    }
}
