package com.investment.investment_platform.services;

import com.investment.investment_platform.domain.Role;
import com.investment.investment_platform.domain.User;
import com.investment.investment_platform.domain.UserRole;
import com.investment.investment_platform.dto.CreateUserRequest;
import com.investment.investment_platform.dto.UserResponseDTO;
import com.investment.investment_platform.exception.RoleAlreadyExistsException;
import com.investment.investment_platform.exception.UserNotFoundException;
import com.investment.investment_platform.repository.RoleRepository;
import com.investment.investment_platform.repository.UserRepository;
import com.investment.investment_platform.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Transactional
    public void assignRole(Long userId, Long roleId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        boolean alreadyAssigned = user.getUserRoles()
                .stream()
                .anyMatch(ur -> ur.getRole().getId().equals(roleId));

        if (alreadyAssigned) {
            throw new RoleAlreadyExistsException("Role already assigned to user");
        }

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRole.setAssignedAt(LocalDateTime.now());

        user.getUserRoles().add(userRole);
    }


    @Transactional
    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setEnabled(true);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);


    }

    public UserResponseDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        List<String> roles = user.getUserRoles()
                .stream()
                .map(ur -> ur.getRole().getName())
                .toList();

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.isEnabled(),
                roles
        );
    }
}
