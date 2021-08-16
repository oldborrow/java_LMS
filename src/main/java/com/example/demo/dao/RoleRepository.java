package com.example.demo.dao;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Override
    List<Role> findAll();

    Optional<Role> findByName(String name);
}
