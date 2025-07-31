package com.AuthService.service;

import com.AuthService.entity.User;
import com.AuthService.exception.InvalidCredentialsException;
import com.AuthService.exception.UserAlreadyExistsException;
import com.AuthService.payload.APIResponse;
import com.AuthService.payload.LoginDto;
import com.AuthService.payload.SignupDTO;
import com.AuthService.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authManager;
    private JWTService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public APIResponse<String> userSignup(SignupDTO signupDTO, String role){
        APIResponse<String> response = new APIResponse<>();
        if(userRepository.existsByUsername(signupDTO.getUsername())){
            throw new UserAlreadyExistsException("Username already exists");
        }
        if(userRepository.existsByEmailId(signupDTO.getEmailId())){
            throw new UserAlreadyExistsException("Email ID already exists");
        }
        User user = new User();
        user.setRole(role);
        BeanUtils.copyProperties(signupDTO, user);
        String encodedPassword = passwordEncoder.encode(signupDTO.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        response.setMessage("Registration Successful");
        response.setStatus(202);
        response.setData("User Registered Successfully");
        return response;

    }

    public APIResponse<String> userLogin(LoginDto dto){
        APIResponse<String> response = new APIResponse<>();
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        try {
            Authentication authenticate = authManager.authenticate(token);
            if (!authenticate.isAuthenticated()) {
                throw new InvalidCredentialsException("Invalid username or password");
            }
            if(authenticate.isAuthenticated()){
                String jwtToken = jwtService.generateToken(dto.getUsername(), authenticate.getAuthorities().iterator().next().getAuthority());
                response.setMessage("Login Successful");
                response.setStatus(200);
                response.setData(jwtToken);
                return response;
            }
        }catch (AuthenticationException e) {
            throw new InvalidCredentialsException("Invalid username or password");
        }
        response.setMessage("Failed");
        response.setStatus(401);
        response.setData("Un-Authorized Access");
        return response;
    }

}
