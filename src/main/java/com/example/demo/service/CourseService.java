package com.example.demo.service;

import com.example.demo.dao.CourseRepository;
import com.example.demo.domain.Course;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Component
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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
}
