package com.investment.investment_platform;

import com.investment.investment_platform.domain.Role;
import com.investment.investment_platform.domain.User;
import com.investment.investment_platform.repository.RoleRepository;
import com.investment.investment_platform.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;



    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {

    }


}
