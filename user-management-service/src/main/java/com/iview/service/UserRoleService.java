package com.iview.service;

import com.iview.entity.Role;
import com.iview.entity.User;
import com.iview.entity.UserRole;
import com.iview.entity.UserRoleCompositeKey;
import com.iview.repository.RoleRepository;
import com.iview.repository.UserRepository;
import com.iview.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    public void assignRoleToUser(Long userId, String roleName) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleName);

        if (user.isPresent() && role.isPresent()) {
            UserRole userRole = new UserRole();
            userRole.setId(new UserRoleCompositeKey(userId, roleName));
            userRole.setUser(user.get());
            userRole.setRole(role.get());
//            userRole.setAssignedAt(LocalDateTime.now());
            userRoleRepository.save(userRole);
        } else {
            throw new RuntimeException("User or Role not found");
        }
    }
}
