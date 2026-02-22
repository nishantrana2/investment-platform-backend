package com.investment.investment_platform.repository;

import com.investment.investment_platform.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    boolean existsByUserIdAndRoleId(Long userId, Long roleId);
}

