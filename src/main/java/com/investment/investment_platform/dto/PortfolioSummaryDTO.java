package com.investment.investment_platform.dto;

public class PortfolioSummaryDTO {

    private String fundName;
    private Long totalUnits;
    private Double totalInvestment;

    public PortfolioSummaryDTO(String fundName, Long totalUnits, Double totalInvestment) {
        this.fundName = fundName;
        this.totalUnits = totalUnits;
        this.totalInvestment = totalInvestment;
    }

    public String getFundName() {
        return fundName;
    }

    public Long getTotalUnits() {
        return totalUnits;
    }

    public Double getTotalInvestment() {
        return totalInvestment;
    }
}
