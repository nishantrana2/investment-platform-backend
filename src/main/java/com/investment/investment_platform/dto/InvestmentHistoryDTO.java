package com.investment.investment_platform.dto;

import java.time.LocalDateTime;

public class InvestmentHistoryDTO {

    private Long investmentId;
    private String fundName;
    private Integer units;
    private Double pricePerUnit;
    private Double totalAmount;
    private LocalDateTime createdAt;

    public InvestmentHistoryDTO(
            Long investmentId,
            String fundName,
            Integer units,
            Double pricePerUnit,
            Double totalAmount,
            LocalDateTime createdAt
    ) {
        this.investmentId = investmentId;
        this.fundName = fundName;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public Long getInvestmentId() { return investmentId; }
    public String getFundName() { return fundName; }
    public Integer getUnits() { return units; }
    public Double getPricePerUnit() { return pricePerUnit; }
    public Double getTotalAmount() { return totalAmount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
