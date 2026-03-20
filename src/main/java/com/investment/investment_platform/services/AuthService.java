package com.investment.investment_platform.services;

import com.investment.investment_platform.domain.Role;
import com.investment.investment_platform.domain.User;
import com.investment.investment_platform.domain.UserRole;
import com.investment.investment_platform.dto.LoginResponseDTO;
import com.investment.investment_platform.dto.RegisterRequestDTO;
import com.investment.investment_platform.repository.RoleRepository;
import com.investment.investment_platform.repository.UserRepository;
import com.investment.investment_platform.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager,
                       JwtUtil jwtUtil,
                       UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDTO login(String username, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String accessToken = jwtUtil.generateAccessToken(username);
        String refreshToken = jwtUtil.generateRefreshToken(username);

        return new LoginResponseDTO(accessToken, refreshToken);
    }

    public void register(RegisterRequestDTO request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Role role = roleRepository
                .findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Role ADMIN not found"));

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRole.setAssignedAt(LocalDateTime.now());

        user.getUserRoles().add(userRole);

        userRepository.save(user);
    }

    public String refreshToken(String refreshToken) {

        String username = jwtUtil.extractUsername(refreshToken);

        return jwtUtil.generateAccessToken(username);
    }
}
