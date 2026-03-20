package com.investment.investment_platform.Specification;

import com.investment.investment_platform.domain.Fund;
import org.springframework.data.jpa.domain.Specification;

public class FundSpecification {


    public static Specification<Fund> hasCategory(String category) {
        return (root, query, cb) ->
                cb.equal(root.get("category"), category);
    }

    public static Specification<Fund> isActive(Boolean active) {
        return (root, query, cb) ->
                cb.equal(root.get("active"), active);
    }

    public static Specification<Fund> priceGreaterThan(Double minPrice) {
        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("pricePerUnit"), minPrice);
    }

}
