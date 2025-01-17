package com.iview.service;

import com.iview.dto.UserDto;
import com.iview.entity.User;
import com.iview.repository.UserRepository;
import com.iview.util.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(UserDto userDto){
        User user = toEntity(userDto);
        userRepository.save(user);
    }

    private User toEntity(UserDto userDto) {

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUserName());
        user.setPassword(getBcryptPassword(userDto.getPassword()));
//        user.setRole(UserRole.ROLE_USER.toString());
        user.setStatus(UserStatus.PENDING.toString());
        return user;
    }

    private String getBcryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }


    public UserDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null); //TODO throw Excep?
        return toDto(user);
    }

    private UserDto toDto(User user) {
        if(user == null) return null;
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setStatus(user.getStatus());
        return userDto;
    }

    public UserDto findByUserName(String username) {
       User user = userRepository.findByUsername(username).orElse(null);
       return toDto(user);
    }
}
