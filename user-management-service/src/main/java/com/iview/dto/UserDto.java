package com.iview.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto {
    private static final Long SERIAL_VERSION_UID = 1L;

    private Long userId;
    private String userName;
    private String email;
    private String password;
    private String status;
    private String role;
}
