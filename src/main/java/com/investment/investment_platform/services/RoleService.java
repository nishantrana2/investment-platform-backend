package com.investment.investment_platform.services;

import com.investment.investment_platform.domain.Role;
import com.investment.investment_platform.dto.CreateRoleRequest;
import com.investment.investment_platform.exception.RoleAlreadyExistsException;
import com.investment.investment_platform.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void createRole(CreateRoleRequest request) {

        boolean exists = roleRepository.existsByName(request.getName());

        if (exists) {
            throw new RoleAlreadyExistsException(request.getName());
        }

        Role role = new Role();
        role.setName(request.getName());

        roleRepository.save(role);


    }
}
