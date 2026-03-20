package com.investment.investment_platform.dto;


public class PortfolioResponseDTO {

    private String fundName;
    private Integer units;
    private Double pricePerUnit;
    private Double totalAmount;

    public PortfolioResponseDTO(String fundName,
                                Integer units,
                                Double pricePerUnit,
                                Double totalAmount) {
        this.fundName = fundName;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
        this.totalAmount = totalAmount;
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
}
