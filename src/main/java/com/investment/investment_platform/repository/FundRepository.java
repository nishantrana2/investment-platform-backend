package com.investment.investment_platform.repository;

import com.investment.investment_platform.domain.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundRepository extends JpaRepository<Fund,Long> {

}
