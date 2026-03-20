package com.investment.investment_platform.security;

import com.investment.investment_platform.services.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtFilter(JwtUtil jwtUtil,
                     CustomUserDetailsService userDetailsService) {

        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        System.out.println("JWT Filter Triggered");

        String header = request.getHeader("Authorization");

        System.out.println("Authorization Header: " + header);

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            System.out.println("Token: " + token);

            try {

                String username = jwtUtil.extractUsername(token);

                System.out.println("Extracted username: " + username);


                UserDetails userDetails =
                        userDetailsService.loadUserByUsername(username);
                System.out.println("Authorities: " + userDetails.getAuthorities());


                System.out.println("User loaded: " + userDetails.getUsername());

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);

                System.out.println("Authentication set in SecurityContext");

            } catch (Exception e) {

                System.out.println("JWT ERROR: " + e.getMessage());
            }
        } else {

            System.out.println("Authorization header missing or invalid");
        }

        filterChain.doFilter(request, response);
    }
}
