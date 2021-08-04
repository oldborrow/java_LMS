package com.example.demo.dto;

import com.example.demo.domain.Role;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class UserDto {
    private Long id;

    @NotBlank(message = "Password has to be filled")
    private String password;

    @NotBlank(message = "Username has to be filled")
    private String username;

    private Set<Role> roles;

    public UserDto(String username) {
        this.username = username;
    }

    public UserDto(Long id) {
        this.id = id;
    }

    public UserDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserDto() {
    }

    public UserDto(Long id, String username, String s, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = s;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCourseId(Long courseId) {
        courseId = courseId;
    }

    public CharSequence getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
