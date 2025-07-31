package com.AuthService.controller;

import com.AuthService.payload.APIResponse;
import com.AuthService.payload.LoginDto;
import com.AuthService.payload.SignupDTO;
import com.AuthService.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register/admin")
    public ResponseEntity<APIResponse<String>> signupAdmin(@RequestBody @Valid SignupDTO signupDTO){
        APIResponse<String> response = authService.userSignup(signupDTO,"ROLE_ADMIN");
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }
    @PostMapping("/register/user")
    public ResponseEntity<APIResponse<String>> signupUser(@RequestBody @Valid SignupDTO signupDTO){
        APIResponse<String> response = authService.userSignup(signupDTO,"ROLE_USER");
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }
    @PostMapping("/register/inventory-manager")
    public ResponseEntity<APIResponse<String>> signupInventoryManager(@RequestBody @Valid SignupDTO signupDTO){
        APIResponse<String> response = authService.userSignup(signupDTO,"ROLE_INVENTORY_MANAGER");
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }
    @PostMapping("/register/crm")
    public ResponseEntity<APIResponse<String>> signupCRM(@RequestBody @Valid SignupDTO signupDTO){
        APIResponse<String> response = authService.userSignup(signupDTO,"ROLE_CRM");
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }
    @PostMapping("/register/agent")
    public ResponseEntity<APIResponse<String>> signupAgent(@RequestBody @Valid SignupDTO signupDTO){
        APIResponse<String> response = authService.userSignup(signupDTO,"ROLE_AGENT");
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>> userLogin(@RequestBody LoginDto dto){
        APIResponse<String> response = authService.userLogin(dto);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }
}
