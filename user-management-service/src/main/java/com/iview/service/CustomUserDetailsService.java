package com.iview.service;

import com.iview.entity.User;
import com.iview.repository.UserRepository;
import com.iview.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return new CustomUserDetails(user.get().getUsername(), user.get().getPassword(), Arrays.asList(user.get().getRole()));
        }
        else {
            throw new UsernameNotFoundException("User doesn't exist");
        }

    }
}
