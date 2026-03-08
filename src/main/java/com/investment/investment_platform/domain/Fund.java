package com.investment.investment_platform.domain;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "funds")
public class Fund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String category;

    @Column(nullable = false)
    private Double pricePerUnit;

    @Column(nullable = false)
    private Integer totalUnitsAvailable;

    private boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Version
    private Long version;
    protected Fund() {}


    public Fund(Long id, String name, String category, Double pricePerUnit, Integer totalUnitsAvailable, boolean active) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.pricePerUnit = pricePerUnit;
        this.totalUnitsAvailable = totalUnitsAvailable;
        this.active = active;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Integer getTotalUnitsAvailable() {
        return totalUnitsAvailable;
    }

    public void setTotalUnitsAvailable(Integer totalUnitsAvailable) {
        this.totalUnitsAvailable = totalUnitsAvailable;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
