package com.iview.controller;

import com.iview.dto.UserDto;
import com.iview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagerController {

    @Autowired
    UserService userService;

    public UserDto getLoggedInUser(){
        return new UserDto();//TODO
    }
}
