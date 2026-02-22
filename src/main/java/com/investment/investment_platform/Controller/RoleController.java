package com.investment.investment_platform.Controller;


import com.investment.investment_platform.dto.CreateRoleRequest;
import com.investment.investment_platform.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Void>   createRole(  @Valid @RequestBody CreateRoleRequest request){

        roleService.createRole(request);

        return ResponseEntity.noContent().build();

    }


}
