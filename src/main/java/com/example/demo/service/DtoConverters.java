package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;

public class DtoConverters {
    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                "",
                user.getRoles()
        );
    }
}
