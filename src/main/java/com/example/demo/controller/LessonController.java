package com.example.demo.controller;

import com.example.demo.dto.LessonDto;
import com.example.demo.service.LessonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/lesson")
public class LessonController {
    private LessonService lessonService;

    @GetMapping("/new")
    public String lessonForm(Model model, @RequestParam("course_id") long id) {
        model.addAttribute("lesson", new LessonDto(id));
        return "lesson_form";
    }
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public String lessonSubmit(@Valid LessonDto lessonDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return"lesson_form";
        }
        lessonService.save(lessonDto);
        return "redirect:/course/"+lessonDto.getCourseId();
    }
    @RequestMapping("/{id}")
    public String lessonForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("lesson", lessonService.findById(id));
        return "lesson_form";
    }

    @DeleteMapping("/{id}")
    public String deleteLesson(@PathVariable("id") Long id) {
        Long courseId = lessonService.findById(id).getCourseId();
        lessonService.delete(id);
        return "redirect:/course/"+courseId;
    }
}
