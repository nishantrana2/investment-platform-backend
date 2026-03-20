package com.investment.investment_platform.services;

import com.investment.investment_platform.Specification.FundSpecification;
import com.investment.investment_platform.domain.Fund;
import com.investment.investment_platform.dto.FundResponseDTO;
import com.investment.investment_platform.repository.FundRepository;
import com.investment.investment_platform.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class FundService {

    private final FundRepository fundRepository;

    public FundService(FundRepository fundRepository){
        this.fundRepository = fundRepository;
    }


    @Transactional
    public FundResponseDTO createFund( Fund fund){

        Fund saved =   fundRepository.save(fund);

        return new FundResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getCategory(),
                saved.getPricePerUnit(),
                saved.getTotalUnitsAvailable(),
                saved.isActive()
        );


    }

//    public List<FundResponseDTO> getAllFunds(Pageable pageable){
//       return fundRepository.findAll(pageable)
//               .stream()
//               .map(f ->  new FundResponseDTO(
//                       f.getId(),
//                       f.getName(),
//                       f.getCategory(),
//                       f.getPricePerUnit(),
//                       f.getTotalUnitsAvailable(),
//                       f.isActive()
//                     ))
//               .toList();
//    }

//    public Page<FundResponseDTO> getAllFunds(Pageable pageable){
//
//        return fundRepository.findAll(pageable)
//                .map(f -> new FundResponseDTO(
//                        f.getId(),
//                        f.getName(),
//                        f.getCategory(),
//                        f.getPricePerUnit(),
//                        f.getTotalUnitsAvailable(),
//                        f.isActive()
//                ));
//    }

    public Page<FundResponseDTO> getAllFunds(
            String category,
            Boolean active,
            Double minPrice,
            Pageable pageable
    ) {

        Specification<Fund> spec = null;

        if (category != null) {
            spec = FundSpecification.hasCategory(category);
        }

        if (active != null) {
            spec = spec == null
                    ? FundSpecification.isActive(active)
                    : spec.and(FundSpecification.isActive(active));
        }

        if (minPrice != null) {
            spec = spec == null
                    ? FundSpecification.priceGreaterThan(minPrice)
                    : spec.and(FundSpecification.priceGreaterThan(minPrice));
        }

        return fundRepository.findAll(spec, pageable)
                .map(f -> new FundResponseDTO(
                        f.getId(),
                        f.getName(),
                        f.getCategory(),
                        f.getPricePerUnit(),
                        f.getTotalUnitsAvailable(),
                        f.isActive()
                ));
    }

}
