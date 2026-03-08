package com.investment.investment_platform.services;

import com.investment.investment_platform.domain.Fund;
import com.investment.investment_platform.dto.FundResponseDTO;
import com.investment.investment_platform.repository.FundRepository;
import com.investment.investment_platform.repository.RoleRepository;
import jakarta.transaction.Transactional;
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

    public List<FundResponseDTO> getAllFunds(){
       return fundRepository.findAll()
               .stream()
               .map(f ->  new FundResponseDTO(
                       f.getId(),
                       f.getName(),
                       f.getCategory(),
                       f.getPricePerUnit(),
                       f.getTotalUnitsAvailable(),
                       f.isActive()
                     ))
               .toList();
    }

}
