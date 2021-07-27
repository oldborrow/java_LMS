package com.example.demo.dto;

public class UserDto {
    private Long id;

    private String username;

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
}
