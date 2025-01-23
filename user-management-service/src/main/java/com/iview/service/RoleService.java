package com.iview.service;

import com.iview.entity.Role;
import com.iview.entity.UserRole;
import com.iview.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    public List<UserRole> findRoleId(String roleId){
        return userRoleRepository.findAll();
    }

}
