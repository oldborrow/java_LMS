package com.example.demo.service;

import com.example.demo.dao.RoleRepository;
import com.example.demo.domain.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleService {
    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
