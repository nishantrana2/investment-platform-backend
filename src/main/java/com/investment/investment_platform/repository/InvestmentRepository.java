package com.investment.investment_platform.repository;

import com.investment.investment_platform.domain.Investment;
import com.investment.investment_platform.dto.InvestmentResponseDTO;
import com.investment.investment_platform.dto.PortfolioResponseDTO;
import com.investment.investment_platform.dto.PortfolioSummaryDTO;
import com.investment.investment_platform.dto.PortfolioValueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface InvestmentRepository extends JpaRepository<Investment, Long>, JpaSpecificationExecutor<Investment> {


    @Query("""
        SELECT new com.investment.investment_platform.dto.PortfolioResponseDTO(
            f.name,
            i.units,
            i.pricePerUnit,
            i.totalAmount
        )
        FROM Investment i
        JOIN i.fund f
        WHERE i.user.id = :userId
    """)
    List<PortfolioResponseDTO> getUserPortfolio(Long userId);


    @Query("""
                SELECT new com.investment.investment_platform.dto.PortfolioSummaryDTO(
                    f.name,
                    SUM(i.units),
                    SUM(i.totalAmount)
                )
                FROM Investment i
                JOIN i.fund f
                WHERE i.user.id = :userId
                GROUP BY f.name
                """)
    List<PortfolioSummaryDTO> getPortfolioSummary(Long userId);

    @Query("""
            SELECT new com.investment.investment_platform.dto.InvestmentResponseDTO(
                f.name,
                i.units,
                i.pricePerUnit,
                i.totalAmount,
                i.createdAt
            )
            FROM Investment i
            JOIN i.fund f
            WHERE i.user.id = :userId
""")
    Page<InvestmentResponseDTO> findUserInvestments(Long userId, Pageable pageable);

    @Query("""
            SELECT new com.investment.investment_platform.dto.PortfolioValueDTO(
                SUM(i.totalAmount),
                COUNT(DISTINCT i.fund.id)
            )
            FROM Investment i
            WHERE i.user.id = :userId
""")
    PortfolioValueDTO getPortfolioValue(Long userId);
}