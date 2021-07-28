package com.example.demo.controller;

import com.example.demo.domain.Course;
import com.example.demo.dto.LessonDto;
import com.example.demo.service.CourseService;
import com.example.demo.service.LessonService;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final UserService userService;
    private final LessonService lessonService;


    public CourseController(CourseService courseService, UserService userService, LessonService lessonService) {
        this.courseService = courseService;
        this.userService = userService;
        this.lessonService = lessonService;
    }

    @GetMapping
    public String courseTable(Model model, @RequestParam(name = "titlePrefix", required = false) String titlePrefix) {
        model.addAttribute("courses", courseService.findByTitleLike(titlePrefix == null ? "%" : titlePrefix + "%"));
        return "course_table";
    }

    @RequestMapping("/{id}")
    public String courseForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("course", courseService.findById(id));
        model.addAttribute("lessons", lessonService.findAllForLessonIdWithoutText(id));
        return "course_form";
    }

    @PostMapping
    public String submitCourseForm(@Valid Course course, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "course_form";
        }
        courseService.submitCourseForm(course, bindingResult);
        return "redirect:/course";
    }

    @GetMapping("/new")
    public String courseForm(Model model) {
        model.addAttribute("course", new Course());
        return "course_form";
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return "redirect:/course";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}

