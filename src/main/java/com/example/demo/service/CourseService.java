package com.example.demo.service;

import com.example.demo.dao.CourseRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.domain.Course;
import com.example.demo.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Component
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public List<Course> findByTitle(String titlePrefix) {
        return courseRepository.findByTitleLike(titlePrefix + "%");
    }

    public Course findById(Long id) {
        return courseRepository.findById(id).get();
    }

    public void submitCourseForm(Course course, BindingResult bindingResult) {
        courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Optional<Course> findById(long id) {
        return courseRepository.findById(id);
    }

    public List<Course> findByTitleLike(String s) {
        return courseRepository.findByTitleLike(s);
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

    public void assignUserToCourse(Long courseId, Long userId) {
        Course course = courseRepository.getById(courseId);
        User user = userRepository.getById(userId);
        course.getUsers().add(user);
        user.getCourses().add(course);
        courseRepository.save(course);
    }

    public void dismissUserFromCourse(Long courseId, Long userId) {
        Course course = courseRepository.getById(courseId);
        User user = userRepository.getById(userId);
        course.getUsers().remove(user);
        user.getCourses().remove(course);
        courseRepository.save(course);
    }
}
