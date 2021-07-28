package com.example.demo.service;

import com.example.demo.controller.NotFoundException;
import com.example.demo.dao.UserRepository;
import com.example.demo.domain.Course;
import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class UserService {
    private UserRepository userRepository;
    private CourseService courseService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findUsersNotAssignedToCourse(long courseId) {
        return convertListToDto(userRepository.findUsersNotAssignedToCourse(courseId));
    }

    public List<UserDto> getUsersOfCourse(Long id) {
        return convertListToDto(userRepository.getUsersAssignedToCourse(id));
    }

    public void assignUserById(Long id, Long courseId) {
        signUser(id, courseId, false);
    }

    public void unassignUserById(Long id, Long courseId) {
        signUser(id, courseId, true);
    }

    private List<UserDto> convertListToDto(List<User> list) {
        return list.stream().map(u -> new UserDto(
                u.getId(),
                u.getUsername())).collect(Collectors.toList());
    }

    private void signUser(Long id, Long courseId, boolean isDelete) {
        User user = findById(id);
        Course course = courseService.findById(courseId);
        if (isDelete) {
            user.getCourses().remove(course);
            course.getUsers().remove(user);
        } else {
            course.getUsers().add(user);
            user.getCourses().add(course);
        }
        courseService.save(course);
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }
}
