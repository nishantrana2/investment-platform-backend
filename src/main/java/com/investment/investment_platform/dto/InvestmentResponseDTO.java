package com.investment.investment_platform.dto;

import java.time.LocalDateTime;

public class InvestmentResponseDTO {

    private String fundName;
    private Integer units;
    private Double pricePerUnit;
    private Double totalAmount;
    private LocalDateTime createdAt;

    public InvestmentResponseDTO(
            String fundName,
            Integer units,
            Double pricePerUnit,
            Double totalAmount,
            LocalDateTime createdAt
    ) {
        this.fundName = fundName;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public String getFundName() {
        return fundName;
    }

    public Integer getUnits() {
        return units;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
