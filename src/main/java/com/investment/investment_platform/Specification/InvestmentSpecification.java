package com.investment.investment_platform.Specification;

import com.investment.investment_platform.domain.Investment;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class InvestmentSpecification {

    public static Specification<Investment> hasUser(Long userId) {
        return (root, query, cb) ->
                cb.equal(root.get("user").get("id"), userId);
    }

    public static Specification<Investment> hasFund(Long fundId) {
        return (root, query, cb) ->
                cb.equal(root.get("fund").get("id"), fundId);
    }

    public static Specification<Investment> startDate(LocalDate startDate) {
        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(
                        root.get("createdAt"),
                        startDate.atStartOfDay()
                );
    }

    public static Specification<Investment> endDate(LocalDate endDate) {
        return (root, query, cb) ->
                cb.lessThanOrEqualTo(
                        root.get("createdAt"),
                        endDate.atTime(23,59,59)
                );
    }

    public static Specification<Investment> fetchFund() {
        return (root, query, cb) -> {

            root.fetch("fund", JoinType.LEFT);

            return cb.conjunction();
        };
    }

}
