package com.example.demo.service;

import com.example.demo.controller.NotFoundException;
import com.example.demo.dao.CourseRepository;
import com.example.demo.domain.Course;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Component
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void courseTable(Model model, String titlePrefix) {
        model.addAttribute("courses", courseRepository.findByTitleWithPrefix(titlePrefix == null ? "" : titlePrefix));
    }

    public void courseForm(Model model, Long id) {
        model.addAttribute("course", courseRepository.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    public void submitCourseForm(Course course, BindingResult bindingResult) {
        courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.delete(id);
    }
}
