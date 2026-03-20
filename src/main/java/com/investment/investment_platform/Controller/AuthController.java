package com.investment.investment_platform.Controller;

import com.investment.investment_platform.dto.LoginRequestDTO;
import com.investment.investment_platform.dto.LoginResponseDTO;
import com.investment.investment_platform.dto.RefreshRequestDTO;
import com.investment.investment_platform.dto.RegisterRequestDTO;
import com.investment.investment_platform.security.JwtUtil;
import com.investment.investment_platform.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthService authService;



    public AuthController(JwtUtil jwtUtil, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO request) {

        LoginResponseDTO response = authService.login(
                request.getUsername(),
                request.getPassword()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequestDTO request) {

        authService.register(request);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(
            @RequestBody RefreshRequestDTO request
    ) {

        String username = jwtUtil.extractUsername(request.getRefreshToken());

        String accessToken = jwtUtil.generateAccessToken(username);
        String refreshToken = authService.refreshToken(request.getRefreshToken());

        return ResponseEntity.ok(
                new LoginResponseDTO(accessToken, refreshToken)
        );
    }
}
