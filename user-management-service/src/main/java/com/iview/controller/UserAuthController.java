package com.iview.controller;

import com.iview.dto.UserDto;
import com.iview.dto.UserLoginRequest;
import com.iview.security.JwtUtil;
import com.iview.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(propagation = Propagation.REQUIRED, timeout = 2000)
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto user){
        UserDto userDto = userService.registerUser(user);
        return new ResponseEntity<UserDto>(userDto,HttpStatus.CREATED);

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
