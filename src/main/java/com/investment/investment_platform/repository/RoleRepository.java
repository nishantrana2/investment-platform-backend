package com.investment.investment_platform.repository;

import com.investment.investment_platform.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByName(String name);
}
