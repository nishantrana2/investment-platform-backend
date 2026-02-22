package com.investment.investment_platform.Controller;

import com.investment.investment_platform.domain.User;
import com.investment.investment_platform.dto.CreateUserRequest;
import com.investment.investment_platform.dto.UserResponseDTO;
import com.investment.investment_platform.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;

    public  UserController( UserService user) {
        this.userService = user;
    }



    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<Void> assignRole(
            @PathVariable Long userId,
            @PathVariable Long roleId) {

        userService.assignRole(userId, roleId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @Valid @RequestBody CreateUserRequest request) {

        User user = userService.createUser(request);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUser( @PathVariable Long userId){

        UserResponseDTO user= userService.getUser(userId);

     return   ResponseEntity.ok(user);

    }

}
