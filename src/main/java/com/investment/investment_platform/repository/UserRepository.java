package com.investment.investment_platform.repository;

import com.investment.investment_platform.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
