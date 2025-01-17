package com.iview.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String userName;
    private String email;
    private String password;
    private String status;
    private String role;
}
