package com.example.demo.service;

import com.example.demo.controller.NotFoundException;
import com.example.demo.dao.RoleRepository;
import com.example.demo.domain.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleService {
    private final static String DEFAULT_ROLE = "ROLE_STUDENT";

    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role getDefaultRole() {
        return roleRepository.findByName(DEFAULT_ROLE).orElseThrow(() -> new NotFoundException("Default role doesn't exist"));
    }
}
