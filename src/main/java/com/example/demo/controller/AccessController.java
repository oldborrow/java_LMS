package com.example.demo.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    @GetMapping("/access_denied")
    public String accessDenied() {
        return "not_found";
    }
}

