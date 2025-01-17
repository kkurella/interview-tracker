package com.iview.controller;

import com.iview.dto.UserDto;
import com.iview.dto.UserLoginRequest;
import com.iview.entity.User;
import com.iview.security.JwtUtil;
import com.iview.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class UserAuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/public/register")
    @Operation( summary="add new user", description = "API for registering new user.")
    public void addUser(@RequestBody UserDto user){
        userService.addUser(user);
    }

    @PostMapping(value = "/public/login")
    @Operation( summary="user login with valid credentials", description = "API for validating user and generating jwt access token.")
    public String getLoginInfo(@RequestBody UserLoginRequest request) {

        try {
             authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
             return jwtUtil.generateToken(request.getUsername());
        }
        catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed", e);
        }
    }
    @PostMapping(value = "/private/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserDto adminInfo(@RequestBody UserLoginRequest request){
        return userService.findByUserName(request.getUsername());
    }

}
