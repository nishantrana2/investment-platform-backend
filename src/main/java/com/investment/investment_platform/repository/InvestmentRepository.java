package com.investment.investment_platform.repository;

import com.investment.investment_platform.domain.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}